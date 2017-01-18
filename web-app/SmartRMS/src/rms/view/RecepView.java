/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rms.view;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.model.DualListModel;
import rms.common.ComTainer;
import rms.entity.DiningTable;
import rms.entity.MenuItem;
import rms.entity.OrderItem;
import rms.entity.Reservation;
import rms.session.DiningTableFacade;
import rms.session.MenuItemFacade;
import rms.session.ReservationFacade;
import rms.transaction.ReserveManager;

/**
 *
 * @author Tharinda
 */
@ManagedBean(name = "dtRecepView", eager = true)
@SessionScoped
public class RecepView {

    @EJB
    private MenuItemFacade menuItemFacade;

    @EJB
    private DiningTableFacade diningTableFacade;

    @EJB
    private ReservationFacade reservationFacade;

    @EJB
    private ReserveManager reserveManager;

    @PostConstruct
    public void init() {  // INITIALIZATION-------------------------**************************
        
        reserveManager.updateStatus();

        items = (List<MenuItem>) menuItemFacade.findAll();
        Collections.sort(items);
        leftItems = items;
        selectedItems = items;

        tables = diningTableFacade.findAll();

        List<String> itemSource = new ArrayList<String>();
        List<String> itemTarget = new ArrayList<String>();

        for (int i = 0; i < items.size(); i++) {
            MenuItem iter = items.get(i);

            itemSource.add(iter.getMenuIndex() + " - " + iter.getItemName());

        }

        dualitems = new DualListModel<String>(itemSource, itemTarget);

        ComTainer.setMenu(items);
    }

    // <editor-fold defaultstate="collapsed" desc="Ordering Functions. Click on the + sign on the left to edit the code.">
    //------------------Ordering Functions
    private String customerName = "";

    private boolean namevalidate = false;

    public boolean getNamevalidate() {
        if (customerName.equals("")) {
            namevalidate = false;
        } else {
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

    public void proceed() {
        if (rightItems.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Item list is empty!"));
        } else {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            try {
                ec.redirect(ec.getRequestContextPath() + "/users/receptionist/order-interface.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(RecepView.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Reservation Functions. Click on the + sign on the left to edit the code.">
    //-------------------reservation funcions
    public List<DiningTable> getTables() {
        List<DiningTable> retables = new ArrayList<>();
        List<Integer> resTables;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        resTables = (List<Integer>) session.getAttribute("reslist");

        for (DiningTable i : tables) {
            boolean reserved = false;
            for (int j : resTables) {
                if (i.getTableNo() == j) {
                    reserved = true;
                }
            }
            if (!reserved && i.getTableType().equals("RES")) {
                retables.add(i);
            }
        }

        return retables;
    }

    //This is the function that finds all possible combinations;
    public List<List<DiningTable>> getCombinations() {
        List<DiningTable> availableDiningTables = getTables();

        List<List<DiningTable>> nestedList = new ArrayList<>();
        Set<DiningTable> available = new HashSet<>(availableDiningTables); //convert llist to a set
        Set<Set<DiningTable>> powerSet = powerSet(available);

        ArrayList<Integer> uniques = new ArrayList<>();

        for (Set set : powerSet) {
            List<DiningTable> templList = new ArrayList<>(set);
            int uid = 0;
            int seats = 0;
            int min = 1000;
            for (DiningTable table : templList) {
                uid += (int) Math.pow(10, table.getNumOfSeats()); //get a unique key for each combination
                seats += table.getNumOfSeats();
                if (table.getNumOfSeats() < min) {//find smallest table in combination
                    min = table.getNumOfSeats();
                }
            }
            // add if seat combination is unique, not empty, seats are more than customers, seats are not 4 (MAX) more than customers
            if (!uniques.contains(uid) && !templList.isEmpty() && this.customerCount <= seats && seats < (this.customerCount + min) && this.customerCount + 4 > seats) {
                nestedList.add(templList);
                uniques.add(uid);
            }
        }
        return nestedList;
    }

    public static <T> Set<Set<T>> powerSet(Set<T> originalSet) { // this returns powerset of a set
        Set<Set<T>> sets = new HashSet<>();
        if (originalSet.isEmpty()) {
            sets.add(new HashSet<T>());
            return sets;
        }
        List<T> list = new ArrayList<>(originalSet);
        T head = list.get(0);
        Set<T> rest = new HashSet<>(list.subList(1, list.size()));
        for (Set<T> set : powerSet(rest)) {
            Set<T> newSet = new HashSet<>();
            newSet.add(head);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);
        }
        return sets;
    }
    //end of that function

    //view-edit reservations
    private List<Reservation> reservationsList;

    public List<Reservation> getReservationsList() {
        reservationsList = reservationFacade.findAll();
        return reservationsList;
    }

    public void setReservationsList(List<Reservation> reservationsList) {
        this.reservationsList = reservationsList;
    }

    private List<DiningTable> selectedTables;

    public List<DiningTable> getSelectedTables() {
        return selectedTables;
    }

    public void setSelectedTables(List<DiningTable> selectedTables) {
        this.selectedTables = selectedTables;
    }

    private List<DiningTable> tables;

    public void setTables(List<DiningTable> tables) {
        this.tables = tables;
    }

    private String mealTime;

    public String getMealTime() {
        return mealTime;
    }

    public void setMealTime(String mealTime) {
        this.mealTime = mealTime;
    }

    private int customerCount;

    public int getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }

    private Date date1 = new Date();

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    private String customerContact;

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    private String customerEmail = "";

    public void checkAvail() {
        if (date1.before(ComTainer.getOnlyDate(new Date()))) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error!",
                    "The date is set before current date!"));
        } else {

            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            //session.setAttribute("cust_name", customerName);
            session.setAttribute("cust_id", 0);
            session.setAttribute("res_date", new SimpleDateFormat("yyyy-MM-dd").format(date1));
            session.setAttribute("res_time", mealTime);

            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            try {
                ec.redirect(ec.getRequestContextPath() + "/Check Available");
            } catch (IOException ex) {
                Logger.getLogger(RecepView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void reserve() {
        if (selectedTables.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
                    "Please select tables before submitting reservation"));
        } else {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            session.setAttribute("cust_name", customerName);
            session.setAttribute("cust_cont", customerContact);
            session.setAttribute("cust_mail", customerEmail);
            session.setAttribute("cust_id", 0);
            session.setAttribute("res_date", new SimpleDateFormat("yyyy-MM-dd").format(date1));
            session.setAttribute("res_time", mealTime);
            session.setAttribute("table_list", selectedTables);

            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            try {
                ec.redirect(ec.getRequestContextPath() + "/Reserve");
            } catch (IOException ex) {
                Logger.getLogger(RecepView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private Reservation selectedReservation = new Reservation();

    public Reservation getSelectedReservation() {
        return selectedReservation;
    }

    public void setSelectedReservation(Reservation selectedReservation) {
        this.selectedReservation = selectedReservation;
    }

    public String getSelectedResMealTime() {
        if (this.selectedReservation.getMealTime() != null) {
            return this.selectedReservation.getMealTime();
        } else {
            return "LUNCH";
        }
    }

    public void setSelectedResMealTime(String time) {
        this.selectedReservation.setMealTime(time);
    }

    public String getSelectedResStatus() {
        if (this.selectedReservation.getStatus() != null) {
            return this.selectedReservation.getStatus();
        }
        else{
            return "PENDING";
        }
    }

    public void setSelectedResStatus(String status) {
        this.selectedReservation.setStatus(status);
    }
    
    public void save(Reservation reservation){
        reserveManager.save(reservation);
    }

    //---------------------------------------
    // </editor-fold>
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

    public List<MenuItem> getItems() {
        items = menuItemFacade.findAll();
        return items;
    }

    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

    public void filtertoo() {
        rightItems.clear();
        for (MenuItem i : items) {
            for (String s : dualitems.getTarget()) {
                if (s.split(" - ")[1].trim().toLowerCase().equalsIgnoreCase(i.getItemName())) {
                    //OrderItem oi = new OrderItem(i.getItemId(), 1);
                    OrderItem oi = new OrderItem(1, i);
                    Container c = new Container(i, oi);
                    rightItems.add(c);
                }
            }

        }
    }

    public List<Container> getRightItems() {
        return rightItems;
    }

    private List<MenuItem> selectedItems;

    public List<MenuItem> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<MenuItem> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public void clearList() {
        selectedItems.clear();
    }

//    private boolean hiddenatrib = false;
//
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
