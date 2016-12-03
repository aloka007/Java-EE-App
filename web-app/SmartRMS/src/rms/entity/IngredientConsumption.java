/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rms.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tharinda
 */
@Entity
@Table(name = "ingredient_consumption")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IngredientConsumption.findAll", query = "SELECT i FROM IngredientConsumption i"),
    @NamedQuery(name = "IngredientConsumption.findById", query = "SELECT i FROM IngredientConsumption i WHERE i.id = :id"),
    @NamedQuery(name = "IngredientConsumption.findByAction", query = "SELECT i FROM IngredientConsumption i WHERE i.action = :action"),
    @NamedQuery(name = "IngredientConsumption.findByAmount", query = "SELECT i FROM IngredientConsumption i WHERE i.amount = :amount"),
    @NamedQuery(name = "IngredientConsumption.findByTime", query = "SELECT i FROM IngredientConsumption i WHERE i.time = :time")})
public class IngredientConsumption implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 8)
    @Column(name = "action")
    private String action;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "time" , insertable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @JoinColumn(name = "ingredient_id", referencedColumnName = "id")
    @ManyToOne
    private Ingredient ingredientId;

    public IngredientConsumption() {
    }

    public IngredientConsumption(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Ingredient getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Ingredient ingredientId) {
        this.ingredientId = ingredientId;
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
        if (!(object instanceof IngredientConsumption)) {
            return false;
        }
        IngredientConsumption other = (IngredientConsumption) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rms.entity.IngredientConsumption[ id=" + id + " ]";
    }
    
}
