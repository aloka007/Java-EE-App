/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rms.entity;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author Tharinda
 */
public class BillOrder {

    public BillOrder() {
    }
    
    CustomerOrder customerOrder;
    ArrayList<BillOrderItem> items  = new ArrayList<>();

    public ArrayList<BillOrderItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<BillOrderItem> items) {
        this.items = items;
    }
    
    public void addItem(BillOrderItem item){
        this.items.add(item);
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }


}
