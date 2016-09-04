/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rms.test;

/**
 *
 * @author Tharinda
 */
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import rms.entity.MenuItem;
import rms.session.MenuItemFacade;

@ManagedBean(name = "itemService")
@ApplicationScoped
public class ItemService {

    private List<MenuItem> items;

    @EJB
    private MenuItemFacade menuItemFacade;

    public MenuItemFacade getMenuItemFacade() {
        return menuItemFacade;
    }

    public void setMenuItemFacade(MenuItemFacade menuItemFacade) {
        this.menuItemFacade = menuItemFacade;
    }

//    @PostConstruct
//    public void init() {
//        items = menuItemFacade.findAll();
//    }

    public List<MenuItem> getItems() {
        items = menuItemFacade.findAll();
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }
}
