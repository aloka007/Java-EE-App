/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rms.view;

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

}
