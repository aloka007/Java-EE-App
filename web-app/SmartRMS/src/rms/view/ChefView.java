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
import rms.test.OrderService;
import rms.test.Order;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import rms.common.ComTainer;
import rms.entity.CustomerOrder;
import rms.entity.OrderItem;
import rms.session.CustomerOrderFacade;


@ManagedBean(name = "dtOrderList")
@SessionScoped
public class ChefView implements Serializable {

    boolean hiddenatrib = false;
    EntityManager em;

    int refreshcount = 0;
    private List<CustomerOrder> orders;
    private List<Container> orderDetails;
    
    private CustomerOrder selectedOrder;

    public CustomerOrder getSelectedOrder() {
        return selectedOrder;
    }

    public void setSelectedOrder(CustomerOrder selectedOrder) {
        this.selectedOrder = selectedOrder;
    }

    public List<Container> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<Container> orderDetails) {
        this.orderDetails = orderDetails;
    }


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
    @EJB
    private CustomerOrderFacade customerOrderFacade;

//    @ManagedProperty("#{orderService}")
//    private OrderService service;

    @PostConstruct
    public void init() {
        update();
    }
    
    public void update(){
        orders = customerOrderFacade.findAll();
//        for(CustomerOrder o : orders){
//            Query query = em.createQuery("SELECT i FROM OrderItem i WHERE i.orderNo.orderNo = :order");
//            query.setParameter("order", o.getOrderNo());
//            List<OrderItem> itemlist = (List<OrderItem>) query.getResultList();
//            orderDetails.add(new Container(o, itemlist));
//        }
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

}
