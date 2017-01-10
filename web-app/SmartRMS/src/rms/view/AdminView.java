/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rms.view;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.LineChartSeries;
import rms.entity.Bill;
import rms.session.BillFacade;
import rms.common.ComTainer;
import rms.entity.Customer;
import rms.entity.CustomerOrder;
import rms.entity.Ingredient;
import rms.entity.IngredientConsumption;
import rms.entity.MenuItem;
import rms.entity.MenuItemIngredient;
import rms.entity.OrderItem;
import rms.entity.UserAccount;
import rms.session.CustomerFacade;
import rms.session.CustomerOrderFacade;
import rms.session.IngredientConsumptionFacade;
import rms.session.IngredientFacade;
import rms.session.MenuItemFacade;
import rms.session.MenuItemIngredientFacade;
import rms.session.UserAccountFacade;
import rms.transaction.OrderManager;
import rms.transaction.UserManager;

/**
 *
 * @author Tharinda
 */
@ManagedBean(name = "AdmV")
@SessionScoped
public class AdminView {
    
    @PersistenceContext(unitName = "SmartRMSPU")
    private EntityManager em;

    @PostConstruct
    public void init() {
        ingredientList = (List<Ingredient>) ingredientFacade.findAll();
        billList = (List<Bill>) billFacade.findAll();
        consumptionList = (List<IngredientConsumption>) ingredientConsumptionFacade.findAll();
        customers  = (List<Customer>) customerFacade.findAll();
        userList = (List<UserAccount>) userAccountFacade.findAll();
        rulesList = menuItemIngredientFacade.findAll();
        dummyAccount = new UserAccount();
        createDateModel();
        initBarModel();
        initBarModel2();
    }

    // <editor-fold defaultstate="collapsed" desc=" Sales Components ">
    @EJB
    private BillFacade billFacade;

    private LineChartModel salesChart;

    List<Bill> billList = new ArrayList<>();

    public List<Bill> getBillList() {
        return billList;
    }

    public void setBillList(List<Bill> billList) {
        this.billList = billList;
    }

    public LineChartModel getDateModel() {
        return salesChart;
    }

    private void createDateModel() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Random r = new Random();
        salesChart = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");
        series1.setSmoothLine(false);

        //String mindate = "0";

        try { //create salesbye date graph
            Date tempDate = new SimpleDateFormat("yyyy-MM-dd").parse("2016-11-02");
            Date lastDate = ComTainer.addDays((new Date()), 1);

            //Date lastDate = new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-31");
            while (tempDate.before(lastDate)) {
                BigDecimal tempTotal = BigDecimal.valueOf(0);
                for (Bill bill : billList) {
                    if (bill.getDate().after(tempDate) && bill.getDate().before(ComTainer.addDays(tempDate, 1))) {
                        BigDecimal dec = bill.getSubTotal();
                        tempTotal = tempTotal.add(dec);
                    }
                }
                series1.set(df.format(tempDate), tempTotal.toBigInteger().intValue());
                tempDate = ComTainer.addDays(tempDate, 1);
            }
        } catch (ParseException ex) {
            Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex);
        }

        salesChart.addSeries(series1);
        salesChart.setAnimate(true);

        salesChart.setTitle("Zoom for Details");
        salesChart.setZoom(true);
        salesChart.getAxis(AxisType.Y).setLabel("Sales");
        DateAxis axis = new DateAxis("Date");
        axis.setTickAngle(-50);
        axis.setMin("2016-10-31");
        axis.setMax(new SimpleDateFormat("yyyy-MM-dd").format(ComTainer.addDays((new Date()), 1)));
        axis.setTickFormat("%b %#d, %y");

        salesChart.getAxes().put(AxisType.X, axis);
    }

    public BigDecimal getTodaySales() {
        BigDecimal t_total = BigDecimal.valueOf(0);
        Date today = new Date();
        today = ComTainer.getOnlyDate(today);
        for (Bill bill : billList) {
            if (ComTainer.getOnlyDate(bill.getDate()).equals(today)) {
                t_total = t_total.add(bill.getSubTotal());
            }
        }
        return t_total;
    }
    Date startDate = ComTainer.getOnlyDate(ComTainer.addDays(new Date(), -7));
    Date endDate = ComTainer.getOnlyDate(ComTainer.addDays(new Date(), 1));

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getPeriodTotal() {
        BigDecimal t_total = BigDecimal.valueOf(0);
        for (Bill bill : billList) {
            if (ComTainer.getOnlyDate(bill.getDate()).before(endDate) && ComTainer.getOnlyDate(bill.getDate()).after(startDate)) {
                t_total = t_total.add(bill.getSubTotal());
            }
        }
        return t_total;
    }

    public String getToday() {
        Date today = new Date();
        return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(today);
    }

    // </editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Stocks Components">
    @EJB
    IngredientFacade ingredientFacade;

    @EJB
    IngredientConsumptionFacade ingredientConsumptionFacade;

    List<Ingredient> ingredientList = new ArrayList<>();

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    List<IngredientConsumption> consumptionList = new ArrayList<>();

    public List<IngredientConsumption> getConsumptionList() {
        return consumptionList;
    }

    public void setConsumptionList(List<IngredientConsumption> consumptionList) {
        this.consumptionList = consumptionList;
    }

    private BarChartModel stocksBarModel = new BarChartModel();

    private LineChartModel consumptionChart;

    public LineChartModel getConsumptionChart() {
        return consumptionChart;
    }

    public BarChartModel getStocksBarModel() {
        return stocksBarModel;
    }

    private void initBarModel() { //create ingredient level graph
        stocksBarModel.setTitle("Stock Levels");
        stocksBarModel.setAnimate(true);
        Axis xAxis = stocksBarModel.getAxis(AxisType.X);
        xAxis.setLabel("Ingredient");
        Axis yAxis = stocksBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("Amount (grams)");

        ChartSeries bar = new ChartSeries();

        for (Ingredient ingredient : ingredientList) {
            if (ingredient.getUnit().equals("UNIT")) {
                bar.set(ingredient.getName(), ingredient.getAmount().multiply(BigDecimal.valueOf(1)).toBigInteger().intValue());
            } else {
                bar.set(ingredient.getName(), ingredient.getAmount().multiply(BigDecimal.valueOf(1000)).toBigInteger().intValue());
            }
        }
        stocksBarModel.addSeries(bar);
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Menu Items Components">
    @EJB
    private CustomerOrderFacade customerOrderFacade;

    @EJB
    private MenuItemFacade menuItemFacade;

    private HorizontalBarChartModel itemsBarModel = new HorizontalBarChartModel();

    public HorizontalBarChartModel getItemsBarModel() {
        return itemsBarModel;
    }

    private void initBarModel2() {
        List<MenuItem> menuItems = menuItemFacade.findAll();
        List<OrderItem> combinedList = new ArrayList<>();

        for (MenuItem menuItem : menuItems) {
            OrderItem oi = new OrderItem(0, menuItem);
            combinedList.add(oi);
        }

        List<CustomerOrder> orders = customerOrderFacade.findAll();
        itemsBarModel.setTitle("Item-wise Sales");
        itemsBarModel.setAnimate(true);
        Axis xAxis = itemsBarModel.getAxis(AxisType.X);
        xAxis.setLabel("Sales");
        Axis yAxis = itemsBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("Items");

        ChartSeries bar = new ChartSeries();

        for (CustomerOrder order : orders) { //creating the graph for item wise sales
            List<OrderItem> itemList = (List<OrderItem>) order.getOrderItemCollection();
            for (OrderItem orderItem : itemList) {
                MenuItem item = orderItem.getItemId();

                for (OrderItem orderItem1 : combinedList) {
                    if (orderItem1.getItemId().getItemId().equals(item.getItemId())) {
                        orderItem1.setQuantity(orderItem1.getQuantity() + orderItem.getQuantity());
                    }
                }
            }
        }

        for (OrderItem orderItem1 : combinedList) {
            bar.set(orderItem1.getItemId().getItemName(), orderItem1.getQuantity());
        }
        itemsBarModel.addSeries(bar);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Customers Components">
    @EJB
    private CustomerFacade customerFacade;
    
    private List<Customer> customers;

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
    
    public BigDecimal customerTotal(String email){ //calculate total spent by each customer
        BigDecimal total = BigDecimal.valueOf(0.00);
        for (Bill bill : billList) {
            if (bill.getCustomerName() != null && bill.getCustomerName().equals(email)) {
                total = total.add(bill.getSubTotal());
            }
        }
        return total;
    }
            
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="User Account Functions">
    @EJB
    UserAccountFacade userAccountFacade;
    
    @EJB
    UserManager userManager;
    
    private List<UserAccount> userList;
    
    public List<UserAccount> getUserList() {
        return userList;
    }
    
    public void setUserList(List<UserAccount> userList) {
        this.userList = userList;
    }
    
    private UserAccount selcectedAccount;
    
    public UserAccount getSelcectedAccount() {
        return selcectedAccount;
    }
    
    public void setSelcectedAccount(UserAccount selcectedAccount) {
        this.selcectedAccount = selcectedAccount;
    }
    
    private UserAccount dummyAccount;

    public UserAccount getDummyUserAccount() {
        return dummyAccount;
    }

    public void setDummyUserAccount(UserAccount dummyAccount) {
        this.dummyAccount = dummyAccount;
    }
    
    public void editUser(UserAccount user){
        userManager.saveUser(selcectedAccount);
    }
    
    public void createUser(){
        userManager.saveUser(dummyAccount);
        em.getEntityManagerFactory().getCache().evictAll();
        userList = (List<UserAccount>) userAccountFacade.findAll();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Estimation Rules">
    @EJB
            MenuItemIngredientFacade menuItemIngredientFacade;
    
    @EJB
            OrderManager orderManager;
    
    private List<MenuItemIngredient> rulesList;
    
    public List<MenuItemIngredient> getRulesList() {
        return rulesList;
    }
    
    public void setRulesList(List<MenuItemIngredient> rulesList) {
        this.rulesList = rulesList;
    }
    
    public void createRule(){
        MenuItemIngredient rule = new MenuItemIngredient();
        orderManager.saveNewRule(rule);
        em.getEntityManagerFactory().getCache().evictAll();
        rulesList = menuItemIngredientFacade.findAll();
    }
    
    public void saveRule(){
        orderManager.saveNewRule(rule);
    }
    
    MenuItemIngredient rule;
    
    public MenuItemIngredient getSelectedRule() {
        return rule;
    }
    
    public void setSelectedRule(MenuItemIngredient rule) {
        this.rule = rule;
    }
    
    public void setRuleIngredient(Ingredient ingredient){
        rule.setIngredientId(ingredient);
    }
    public Ingredient getRuleIngredient(){
        return rule.getIngredientId();
    }
    
    public void chooseMenuItem(MenuItem item){
        rule.setItemId(item);
    }
    
//</editor-fold>
    public void navigate(String path) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + "/users/admin/" + path + ".xhtml");
        } catch (IOException ex) {
            Logger.getLogger(RecepView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
