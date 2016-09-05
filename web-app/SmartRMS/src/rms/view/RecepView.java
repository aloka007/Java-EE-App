/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rms.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import org.primefaces.model.DualListModel;
import rms.common.ComTainer;
import rms.entity.MenuItem;
import rms.entity.OrderItem;
import rms.session.MenuItemFacade;

/**
 *
 * @author Tharinda
 */
@ManagedBean(name = "dtRecepView", eager = true)
@SessionScoped
public class RecepView {

    @EJB
    private MenuItemFacade menuItemFacade;

    private boolean hiddenatrib = false;

    //------------------Ordering Functions
    private String customerName = "";
    
    private boolean namevalidate = false;
    
    public boolean getNamevalidate(){
        if(customerName.equals("")){
            namevalidate = false;
        }
        else{
            namevalidate = true;
        }
        return namevalidate;
    }
    
    

    private float total;

    public float getTotal() {
        total = 0;
        for (Container c : rightItems) {
            total += c.getSubtotal();
        }
        return total;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void placeOrder() {

        List<String> itemSource = new ArrayList<String>();
        List<String> itemTarget = new ArrayList<String>();

        for (int i = 0; i < items.size(); i++) {
            MenuItem iter = items.get(i);

            itemSource.add(iter.getMenuIndex() + " - " + iter.getItemName());

        }

        dualitems = new DualListModel<>(itemSource, itemTarget);
    }

    public void submit() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.setAttribute("cust_name", customerName);
        session.setAttribute("item_list", rightItems);
        
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + "/PlaceOrder");
        } catch (IOException ex) {
            Logger.getLogger(RecepView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void newOrder() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + "/NewOrder");
        } catch (IOException ex) {
            Logger.getLogger(RecepView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //-------------------ordering functions
    private DualListModel<String> dualitems;

    public DualListModel<String> getDualitems() {
        return dualitems;
    }

    public void setDualitems(DualListModel<String> dualitems) {
        this.dualitems = dualitems;
    }

    private List<MenuItem> items = new ArrayList<>();
    private List<MenuItem> leftItems = new ArrayList<>();
    private List<Container> rightItems = new ArrayList<>();
    private List<String> strings;

    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

//    private String searchText;
//
//    public String getSearchText() {
//        return searchText;
//    }
//
//    public void setSearchText(String searchText) {
//        this.searchText = searchText;
//    }
//    public void filter() {
//        leftItems = items;
//        for (int j = 0; j < leftItems.size(); j++) {
//            if (!leftItems.get(j).getItemName().toLowerCase().contains(searchText)) {
//                MenuItem temp = leftItems.get(j);
//                leftItems.remove(j);
//                leftItems.add(temp);
//            }
//            j++;
//        }
//        if (searchText.trim().equals("")) {
//            leftItems = items;
//        }
//        
//    }
    public void filtertoo() {
        rightItems.clear();
        for (MenuItem i : items) {
            for (String s : dualitems.getTarget()) {
                if (s.split(" - ")[1].trim().toLowerCase().equalsIgnoreCase(i.getItemName())) {
                    OrderItem oi = new OrderItem(i.getItemId(), 1);
                    Container c = new Container(i, oi);
                    rightItems.add(c);
                }
            }

        }
    }

    public void fill() {
        leftItems = items;
    }

    public void sortList() {
        Collections.sort(items);
    }

    public List<MenuItem> getLeftItems() {
        return leftItems;
    }

    public List<Container> getRightItems() {
        return rightItems;
    }
    private MenuItem selectedItem;

    public MenuItem getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(MenuItem selectedItem) {
        this.selectedItem = selectedItem;
        strings.add(selectedItem.getItemName());

    }

    private List<MenuItem> selectedItems;

    public void clearList() {
        selectedItems.clear();
    }

//    public void updateList() {
//        //RequestContext.getCurrentInstance().update("orderform");
//    }

    public List<MenuItem> getItems() {
        return items;
    }

    public List<MenuItem> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<MenuItem> selectedItems) {
        this.selectedItems = selectedItems;
    }

    @PostConstruct
    public void init() {
        items = (List<MenuItem>) menuItemFacade.findAll();
        Collections.sort(items);
        leftItems = items;
        //selectedItems.add(items.get(0));
        selectedItems = items;

        List<String> itemSource = new ArrayList<String>();
        List<String> itemTarget = new ArrayList<String>();

        for (int i = 0; i < items.size(); i++) {
            MenuItem iter = items.get(i);
//            ComTainer.lisin_itmid.put(i, iter.getItemId());
//            ComTainer.itmid_menit.put(iter.getItemId(), iter);
//            ComTainer.itmid_lisin.put(iter.getItemId(), i);

            itemSource.add(iter.getMenuIndex() + " - " + iter.getItemName());

        }

        dualitems = new DualListModel<String>(itemSource, itemTarget);

        ComTainer.setMenu(items);
    }

    public boolean isHiddenatrib() {
        try {
            String usertype = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("type");
            String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
            int auth_id = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("auth_id");
            int key_id = ComTainer.getKey(username);
            if (usertype.equals("RECEPTIONIST") && key_id == auth_id) {
                hiddenatrib = true;
            }
        } catch (Exception e) {
        }
        return hiddenatrib;
    }

    public void setHiddenatrib(boolean hiddenatrib) {
        this.hiddenatrib = hiddenatrib;
    }
}
