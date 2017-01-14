///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package rms.view;

/**
 *
 * @author Tharinda
 */
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import rms.common.ComTainer;
import rms.entity.CustomerOrder;
import rms.entity.Ingredient;
import rms.entity.MenuItemIngredient;
import rms.entity.OrderItem;
import rms.session.CustomerOrderFacade;
import rms.session.OrderItemFacade;
import rms.transaction.OrderManager;

@ManagedBean(name = "dtOrderList")
@SessionScoped
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ChefView implements Serializable {

    @PersistenceContext(unitName = "SmartRMSPU")
    private EntityManager em;

    @EJB
    private OrderManager orderManager;

    @EJB
    private CustomerOrderFacade customerOrderFacade;

    @EJB
    private OrderItemFacade orderItemFacade;

    private int selectedTab = 0;

    public void setSelectedTab(int selectedTab) {
        this.selectedTab = selectedTab;
    }

    public int getSelectedTab() {
        return selectedTab;
    }

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

    private Container selectedContainer;

    public boolean isHiddenatrib() {
        try {
            String usertype = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("type");
            String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
            int auth_id = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("auth_id");
            int key_id = ComTainer.getKey(username);
            if (usertype.equals("CHEF") && key_id == auth_id) {
                hiddenatrib = true;
            }
        } catch (Exception e) {
        }
        return hiddenatrib;
    }

    public void setHiddenatrib(boolean hiddenatrib) {
        this.hiddenatrib = hiddenatrib;
    }

    @PostConstruct
    public void init() {
        itemList = orderItemFacade.findAll();
        update();
        
    }

    public void accept(int i) {
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

    public boolean checkLevel() {
        if (selectedContainer != null) {
            List<OrderItem> selItems = (List<OrderItem>) selectedContainer.customerOrder.getOrderItemCollection();
            for (OrderItem selItem : selItems) {
                List<MenuItemIngredient> rules = (List<MenuItemIngredient>) selItem.getItemId().getMenuItemIngredientCollection();
                for (MenuItemIngredient rule : rules) {
                    BigDecimal currentStock = rule.getIngredientId().getAmount();
                    BigDecimal est = currentStock.subtract(rule.getAmount().multiply(BigDecimal.valueOf(selItem.getQuantity())));
                    if (-1 == est.compareTo(rule.getIngredientId().getWarn())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public List<Ingredient> getLowIngredients(){
        List<Ingredient> lowIngredients = new ArrayList<>();
        if (selectedContainer != null) {
            List<OrderItem> selItems = (List<OrderItem>) selectedContainer.customerOrder.getOrderItemCollection();
            for (OrderItem selItem : selItems) {
                List<MenuItemIngredient> rules = (List<MenuItemIngredient>) selItem.getItemId().getMenuItemIngredientCollection();
                for (MenuItemIngredient rule : rules) {
                    BigDecimal currentStock = rule.getIngredientId().getAmount();
                    BigDecimal est = currentStock.subtract(rule.getAmount().multiply(BigDecimal.valueOf(selItem.getQuantity())));
                    if (-1 == est.compareTo(rule.getIngredientId().getWarn())) {
                        Ingredient tempIngredient = new Ingredient();
                        tempIngredient.setName(rule.getIngredientId().getName());
                        tempIngredient.setWarn(rule.getIngredientId().getWarn());
                        tempIngredient.setAmount(est);
                        lowIngredients.add(tempIngredient);
                    }
                }
            }
        }
        return lowIngredients;
    }

    public void update() {
        em.getEntityManagerFactory().getCache().evictAll();
        orders = customerOrderFacade.findAll();

        itemList = orderItemFacade.findAll();
        orderDetails = new ArrayList<>();
        completedOrders = new ArrayList<>();
        for (CustomerOrder order : orders) {
            List<OrderItem> templist = new ArrayList<>();
            for (OrderItem i : itemList) {
                if (Objects.equals(i.getOrderNo().getOrderNo(), order.getOrderNo())) {
                    templist.add(i);
                }
            }
            Container cont = new Container(order, templist);
            if (order.getStatus().equals((short) 2)) {
                completedOrders.add(cont);
            } 
            else if (order.getStatus().equals((short) 0) || order.getStatus().equals((short) 1)){
                orderDetails.add(cont);
            }
        }
//        if (orderDetails.size() > orderListSize) {
//            EventBus eventBus = EventBusFactory.getDefault().eventBus();
//            eventBus.publish(CHANNEL, new FacesMessage("kek", "kek"));
//            orderListSize = orderDetails.size();
//        }
    }

    private Ingredient selectedIngredient;  //THINGS realated to ingredient levels NOTICE!!!!!!!!!!!!!! <<<<<<<>>>>>>>>>>>

    public Ingredient getSelectedIngredient() {
        return selectedIngredient;
    }

    public void setSelectedIngredient(Ingredient selectedIngredient) {
        this.selectedIngredient = selectedIngredient;
    }

    public void editStock(Ingredient ingredient) {
        orderManager.saveIngredient(ingredient);
    }

    public void refresh() {
        refreshcount++;
    }

    public List<CustomerOrder> getOrders() {
        update();
        return orders;
    }

    public int getRefreshcount() {
        return refreshcount;
    }

    public Container getSelectedContainer() {
        return selectedContainer;
    }

    public void setSelectedContainer(Container selectedContainer) {
        this.selectedContainer = selectedContainer;
    }

    public String statuSwitch(int m, short i) {
        String s = "";
        if (m == 1) {
            switch (i) {
                case 0:
                    s = "red";
                    break;
                case 1:
                    s = "yellow";
                    break;
                case 2:
                    s = "lime";
                    break;
                case 9:
                    s = "slategray";
                    break;
                default:
                    break;
            }

        } else if (m == 2) {

            switch (i) {
                case 0:
                    s = "New";
                    break;
                case 1:
                    s = "Accepted";
                    break;
                case 2:
                    s = "Ready";
                    break;
                case 9:
                    s = "Cancelled";
                    break;
                default:
                    break;
            }
        }
        return s;
    }
}
