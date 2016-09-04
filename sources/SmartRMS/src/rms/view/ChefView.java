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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import rms.common.ComTainer;


@ManagedBean(name = "dtOrderList")
@SessionScoped
public class ChefView implements Serializable {

    boolean hiddenatrib = false;

    int refreshcount = 0;
    private List<Order> orders;


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

    @ManagedProperty("#{orderService}")
    private OrderService service;

    @PostConstruct
    public void init() {
        orders = service.createOrders(5);
    }

    public void refresh() {
        refreshcount++;
        orders = service.createOrders(5);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setService(OrderService service) {
        this.service = service;
    }

    public int getRefreshcount() {
        return refreshcount;
    }

}
