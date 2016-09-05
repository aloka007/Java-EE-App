/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rms.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Tharinda
 */
@Entity
@Table(name = "customer_order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerOrder.findAll", query = "SELECT c FROM CustomerOrder c"),
    @NamedQuery(name = "CustomerOrder.findByOrderNo", query = "SELECT c FROM CustomerOrder c WHERE c.orderNo = :orderNo"),
    @NamedQuery(name = "CustomerOrder.findByOrderTime", query = "SELECT c FROM CustomerOrder c WHERE c.orderTime = :orderTime"),
    @NamedQuery(name = "CustomerOrder.findByTableNo", query = "SELECT c FROM CustomerOrder c WHERE c.tableNo = :tableNo"),
    @NamedQuery(name = "CustomerOrder.findByOrderedBy", query = "SELECT c FROM CustomerOrder c WHERE c.orderedBy = :orderedBy"),
    @NamedQuery(name = "CustomerOrder.findByCustName", query = "SELECT c FROM CustomerOrder c WHERE c.custName = :custName"),
    @NamedQuery(name = "CustomerOrder.findByStatus", query = "SELECT c FROM CustomerOrder c WHERE c.status = :status")})
public class CustomerOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "order_no")
    private Integer orderNo;
    @Column(name = "order_time", insertable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderTime;
    @Column(name = "table_no")
    private Integer tableNo;
    @Size(max = 50)
    @Column(name = "ordered_by")
    private String orderedBy;
    @Size(max = 50)
    @Column(name = "cust_name")
    private String custName;
    @Column(name = "status")
    private Short status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderNo")
    private Collection<OrderItem> orderItemCollection;

    public CustomerOrder() {
    }

    public CustomerOrder(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getTableNo() {
        return tableNo;
    }

    public void setTableNo(Integer tableNo) {
        this.tableNo = tableNo;
    }

    public String getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(String orderedBy) {
        this.orderedBy = orderedBy;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public Short getAccepted() {
        return status;
    }

    public void setAccepted(Short status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<OrderItem> getOrderItemCollection() {
        return orderItemCollection;
    }

    public void setOrderItemCollection(Collection<OrderItem> orderItemCollection) {
        this.orderItemCollection = orderItemCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderNo != null ? orderNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerOrder)) {
            return false;
        }
        CustomerOrder other = (CustomerOrder) object;
        if ((this.orderNo == null && other.orderNo != null) || (this.orderNo != null && !this.orderNo.equals(other.orderNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rms.entity.CustomerOrder[ orderNo=" + orderNo + " ]";
    }
    
}
