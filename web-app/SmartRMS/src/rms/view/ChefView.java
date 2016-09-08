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
import com.sun.javafx.scene.control.skin.VirtualFlow;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.emptyType;
import rms.test.OrderService;
import rms.test.Order;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import rms.common.ComTainer;
import rms.entity.CustomerOrder;
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

    boolean hiddenatrib = false;

    int refreshcount = 0;
    private List<CustomerOrder> orders;
    private List<Container> orderDetails = new ArrayList<>();

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

//    @ManagedProperty("#{orderService}")
//    private OrderService service;
    @PostConstruct
    public void init() {
        itemList = orderItemFacade.findAll();
        update();
    }

    public void accept(int i) {
        selectedContainer.customerOrder.setStatus((short) i);
        orderManager.acceptOrder(selectedContainer.customerOrder);

    }

    public void update() {
        orders = customerOrderFacade.findAll();
        em.getEntityManagerFactory().getCache().evictAll();

        itemList = orderItemFacade.findAll();
        orderDetails = new ArrayList<>();
        for (CustomerOrder order : orders) {
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

    public void refresh() {
        refreshcount++;
    }

    public List<CustomerOrder> getOrders() {
        update();
        return orders;
    }

//    public void setService(OrderService service) {
//        this.service = service;
//    }
    public int getRefreshcount() {
        return refreshcount;
    }

    public Container getSelectedContainer() {
        return selectedContainer;
    }

    public void setSelectedContainer(Container selectedContainer) {
        this.selectedContainer = selectedContainer;
    }

}
