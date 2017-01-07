/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rms.view;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.TabChangeEvent;
import rms.common.ComTainer;
import rms.entity.Bill;
import rms.entity.BillOrder;
import rms.entity.BillOrderItem;
import rms.entity.CustomerOrder;
import rms.entity.OrderItem;
import rms.session.CustomerOrderFacade;
import rms.session.OrderItemFacade;
import rms.transaction.OrderManager;

/**
 *
 * @author Tharinda
 */
@ManagedBean(name = "CashV")
@SessionScoped
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CashierView {

    @PostConstruct
    public void init() {
        itemList = orderItemFacade.findAll();
        update();
        for (int i = 0; i < 12; i++) {
            filterValues.add(String.valueOf(i));
        }
    }

    @PersistenceContext(unitName = "SmartRMSPU")
    private EntityManager em;

    @EJB
    private OrderManager orderManager;

    @EJB
    private CustomerOrderFacade customerOrderFacade;

    @EJB
    private OrderItemFacade orderItemFacade;

    boolean hiddenatrib = false;

    int refreshcount = 0;
    private List<CustomerOrder> orders;
    private List<Container> orderDetails = new ArrayList<>();
    private List<Container> completedOrders = new ArrayList<>();

    public List<Container> getCompletedOrders() {
        return completedOrders;
    }

    private CustomerOrder selectedOrder;

    public CustomerOrder getSelectedOrder() {
        return selectedOrder;
    }

    public void setSelectedOrder(CustomerOrder selectedOrder) {
        this.selectedOrder = selectedOrder;
    }

    private List<OrderItem> itemList;

    public List<OrderItem> getItemList() {

        return itemList;
    }

    public void refreshList() {
        List<OrderItem> templist = new ArrayList<>();
        for (OrderItem i : itemList) {
            if (Objects.equals(i.getOrderNo().getOrderNo(), selectedOrder.getOrderNo())) {
                templist.add(i);
            }
        }
        itemList = templist;
    }

    public List<Container> getOrderDetails() {
        update();
        return orderDetails;
    }

    public void setOrderDetails(List<Container> orderDetails) {
        this.orderDetails = orderDetails;
    }

    private Container selectedContainer; //container

    public Container getSelectedContainer() {
        return selectedContainer;
    }

    public void setSelectedContainer(Container selectedContainer) {
        this.selectedContainer = selectedContainer;
    }

    private List<Container> selectedContainers; // containers

    public List<Container> getSelectedContainers() {
        return selectedContainers;
    }

    public void setSelectedContainers(List<Container> selectedContainers) {
        this.selectedContainers = selectedContainers;
    }

    private List filteredContainers; //filtered containers

    public List getFilteredContainers() {
        return filteredContainers;
    }

    public void setFilteredContainers(List filteredContainers) {
        this.filteredContainers = filteredContainers;
    }

    private List filterValues = new ArrayList();

    public List getFilterValues() {
        return filterValues;
    }

    public void setFilterValues(List filterValues) {
        this.filterValues = filterValues;
    }

    private String customerName =""; //customer name

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    private String payMode = "CASH";

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }
    
    private Bill bill; //the bill object that's being created

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
    
    public void save(){
        if (customerName.trim().equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error!", "Customer name cannot be empty!"));
        }
        else{
        bill.setCustomerName(customerName);
        bill.setSubTotal((bill.getAmount().subtract(bill.getDiscount())).add(bill.getTax()));
        bill.setTotal(bill.getSubTotal().add(bill.getTip()));
        bill.setPayMode(payMode);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.setAttribute("bill", bill);
            
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            try {
                ec.redirect(ec.getRequestContextPath() + "/Save");
            } catch (IOException ex) {
                Logger.getLogger(RecepView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    
    public void proceed() {
        if (selectedContainers.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Orders list is empty!"));
        } 
        else {
            this.bill = new Bill(); //create a bill object
            BigDecimal amount = BigDecimal.valueOf(0);
            for(Container cont : selectedContainers){ //iterate through the containers
                BillOrder billOrder = new BillOrder(); // create a new billorder
                billOrder.setCustomerOrder(cont.customerOrder); //set the customer order
                for(OrderItem item : cont.getItemlist()){ //iterate through order items
                    BillOrderItem billItem = new BillOrderItem();
                    billItem.setOrderItem(item);
                    billItem.setAmount(BigDecimal.valueOf(item.getQuantity()*item.getItemId().getPrice())); //set the amount
                    billOrder.addItem(billItem);
                    amount = amount.add(billItem.getAmount());
                }
                this.bill.addOrder(billOrder); //add it to bill=
            }
            this.customerName = selectedContainers.get(0).getCustomerOrder().getCustName();
            this.bill.setAmount(amount);
            this.bill.setDiscount(BigDecimal.valueOf(0.0));
            this.bill.setTax(amount.multiply(BigDecimal.valueOf(0.15)).setScale(2, RoundingMode.CEILING));
            
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            try {
                ec.redirect(ec.getRequestContextPath() + "/users/cashier/billing.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(RecepView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void accept(int i) {
        if (i == 9 && (selectedContainer.customerOrder.getStatus() == 1 || selectedContainer.customerOrder.getStatus() == 2)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Order is already accepted!"));
        } else {

            if (i == 2) {
                completedOrders.add(selectedContainer);
            }
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            String uName = (String) session.getAttribute("username");
            selectedContainer.customerOrder.setStatus((short) i);
            selectedContainer.customerOrder.setAcceptedBy(uName);
            orderManager.acceptOrder(selectedContainer.customerOrder);
        }

    }

    public void update() {
        em.getEntityManagerFactory().getCache().evictAll();
        orders = customerOrderFacade.findAll();

        itemList = orderItemFacade.findAll();
        orderDetails = new ArrayList<>();
        completedOrders = new ArrayList<>();
        for (CustomerOrder order : orders) {
            if (order.getStatus().equals((short) 3)) {
                List<OrderItem> templist = new ArrayList<>();
                for (OrderItem i : itemList) {
                    if (Objects.equals(i.getOrderNo().getOrderNo(), order.getOrderNo())) {
                        templist.add(i);
                    }
                }
                Container cont = new Container(order, templist);
                orderDetails.add(cont);
            }
        }
    }

    private int selectedTab = 0; // selected tab

    public void setSelectedTab(int selectedTab) {
        this.selectedTab = selectedTab;
    }

    public int getSelectedTab() {
        return selectedTab;
    }

    public void onTabChange(TabChangeEvent event) {
        selectedTab = Integer.parseInt(event.getTab().getId().substring(3, 4));
    }

//    public String statuSwitch(int m, short i) {
//        String s = "";
//        if (m == 1) {
//            switch (i) {
//                case 0:
//                    s = "red";
//                    break;
//                case 1:
//                    s = "darkorange";
//                    break;
//                case 2:
//                    s = "lime";
//                    break;
//                case 3:
//                    s = "turquoise";
//                    break;
//                case 9:
//                    s = "slategray";
//                    break;
//                default:
//                    break;
//            }
//
//        } else if (m == 2) {
//
//            switch (i) {
//                case 0:
//                    s = "New";
//                    break;
//                case 1:
//                    s = "Accepted";
//                    break;
//                case 2:
//                    s = "Ready";
//                    break;
//                case 3:
//                    s = "Delivered";
//                    break;
//                case 9:
//                    s = "Cancelled";
//                    break;
//                default:
//                    break;
//            }
//        }
//        return s;
//    }

//    public boolean isHiddenatrib() {
//        try {
//            String usertype = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("type");
//            String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
//            int auth_id = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("auth_id");
//            int key_id = ComTainer.getKey(username);
//            if (usertype.equals("RECEPTIONIST") && key_id == auth_id) {
//                hiddenatrib = true;
//            }
//        } catch (Exception e) {
//        }
//        //return hiddenatrib;
//        return true;
//    }
}
