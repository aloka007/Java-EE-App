/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rms.entity;

import rms.entity.MenuItem;
import rms.entity.CustomerOrder;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tharinda
 */
@Entity
@Table(name = "order_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderItem.findAll", query = "SELECT o FROM OrderItem o"),
    @NamedQuery(name = "OrderItem.findByOrderItemId", query = "SELECT o FROM OrderItem o WHERE o.orderItemId = :orderItemId"),
    @NamedQuery(name = "OrderItem.findByQuantity", query = "SELECT o FROM OrderItem o WHERE o.quantity = :quantity")})
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "order_item_id")
    private Integer orderItemId;
    @Column(name = "quantity")
    private Integer quantity;
    @JoinColumn(name = "item_id", referencedColumnName = "item_id")
    @ManyToOne(optional = false)
    private MenuItem itemId;
    @JoinColumn(name = "order_no", referencedColumnName = "order_no")
    @ManyToOne(optional = false)
    private CustomerOrder orderNo;

    public OrderItem() {
    }

    public OrderItem(Integer quantity, MenuItem itemId) {
        this.quantity = quantity;
        this.itemId = itemId;
    }
    
    

    public OrderItem(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Integer getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public MenuItem getItemId() {
        return itemId;
    }

    public void setItemId(MenuItem itemId) {
        this.itemId = itemId;
    }

    public CustomerOrder getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(CustomerOrder orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderItemId != null ? orderItemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderItem)) {
            return false;
        }
        OrderItem other = (OrderItem) object;
        if ((this.orderItemId == null && other.orderItemId != null) || (this.orderItemId != null && !this.orderItemId.equals(other.orderItemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rms.common.OrderItem[ orderItemId=" + orderItemId + " ]";
    }
    
}
