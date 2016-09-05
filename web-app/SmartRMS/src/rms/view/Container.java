/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rms.view;

import java.util.List;
import rms.entity.CustomerOrder;
import rms.entity.MenuItem;
import rms.entity.OrderItem;

/**
 *
 * @author Tharinda
 */
public class Container {
    MenuItem menuItem;
    OrderItem orderItem;
    
    
    float subtotal;

    public float getSubtotal() {
        subtotal = (float)(menuItem.getPrice() * orderItem.getQuantity());
        return subtotal;
    }

    public Container(MenuItem menuItem, OrderItem orderItem) {
        this.menuItem = menuItem;
        this.orderItem = orderItem;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }
    
    //chef items
    
    CustomerOrder customerOrder;
    List<OrderItem> itemlist;

    public Container(CustomerOrder customerOrder, List<OrderItem> itemlist) {
        this.customerOrder = customerOrder;
        this.itemlist = itemlist;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public List<OrderItem> getItemlist() {
        return itemlist;
    }

    public void setItemlist(List<OrderItem> itemlist) {
        this.itemlist = itemlist;
    }
    
    

}
