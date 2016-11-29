/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rms.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tharinda
 */
@Entity
@Table(name = "bill")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bill.findAll", query = "SELECT b FROM Bill b"),
    @NamedQuery(name = "Bill.findById", query = "SELECT b FROM Bill b WHERE b.id = :id"),
    @NamedQuery(name = "Bill.findByCustomerName", query = "SELECT b FROM Bill b WHERE b.customerName = :customerName"),
    @NamedQuery(name = "Bill.findByDate", query = "SELECT b FROM Bill b WHERE b.date = :date"),
    @NamedQuery(name = "Bill.findByAmount", query = "SELECT b FROM Bill b WHERE b.amount = :amount"),
    @NamedQuery(name = "Bill.findByDiscount", query = "SELECT b FROM Bill b WHERE b.discount = :discount"),
    @NamedQuery(name = "Bill.findByTax", query = "SELECT b FROM Bill b WHERE b.tax = :tax"),
    @NamedQuery(name = "Bill.findBySubTotal", query = "SELECT b FROM Bill b WHERE b.subTotal = :subTotal"),
    @NamedQuery(name = "Bill.findByTip", query = "SELECT b FROM Bill b WHERE b.tip = :tip"),
    @NamedQuery(name = "Bill.findByTotal", query = "SELECT b FROM Bill b WHERE b.total = :total")})
public class Bill implements Serializable {

    @Size(max = 50)
    @Column(name = "waiter_id")
    private String waiterId;
    @Size(max = 50)
    @Column(name = "cashier_id")
    private String cashierId;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "discount")
    private BigDecimal discount;
    @Column(name = "tax")
    private BigDecimal tax;
    @Column(name = "sub_total")
    private BigDecimal subTotal;
    @Column(name = "tip")
    private BigDecimal tip = BigDecimal.valueOf(0);
    @Column(name = "total")
    private BigDecimal total;
    
    @Transient
    private ArrayList<BillOrder> orders = new ArrayList<>();

    public ArrayList<BillOrder> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList orders) {
        this.orders = orders;
    }
    
    public void addOrder(BillOrder order){
        this.orders.add(order);
    }
    

    public Bill() {
    }

    public Bill(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount.setScale(2, RoundingMode.CEILING);
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount.setScale(2, RoundingMode.CEILING);
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax.setScale(2, RoundingMode.CEILING);
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal.setScale(2, RoundingMode.CEILING);
    }

    public BigDecimal getTip() {
        return tip;
    }

    public void setTip(BigDecimal tip) {
        this.tip = tip.setScale(2, RoundingMode.CEILING);
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total.setScale(2, RoundingMode.CEILING);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bill)) {
            return false;
        }
        Bill other = (Bill) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rms.entity.Bill[ id=" + id + " ]";
    }

    public String getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(String waiterId) {
        this.waiterId = waiterId;
    }

    public String getCashierId() {
        return cashierId;
    }

    public void setCashierId(String cashierId) {
        this.cashierId = cashierId;
    }
    
}
