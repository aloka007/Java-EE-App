/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rms.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tharinda
 */
@Entity
@Table(name = "menu_item_ingredient")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MenuItemIngredient.findAll", query = "SELECT m FROM MenuItemIngredient m"),
    @NamedQuery(name = "MenuItemIngredient.findById", query = "SELECT m FROM MenuItemIngredient m WHERE m.id = :id"),
    @NamedQuery(name = "MenuItemIngredient.findByAmount", query = "SELECT m FROM MenuItemIngredient m WHERE m.amount = :amount")})
public class MenuItemIngredient implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private BigDecimal amount;
    @JoinColumn(name = "ingredient_id", referencedColumnName = "id")
    @ManyToOne
    private Ingredient ingredientId;
    @JoinColumn(name = "item_id", referencedColumnName = "item_id")
    @ManyToOne
    private MenuItem itemId;

    public MenuItemIngredient() {
    }

    public MenuItemIngredient(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Ingredient getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Ingredient ingredientId) {
        this.ingredientId = ingredientId;
    }

    public MenuItem getItemId() {
        return itemId;
    }

    public void setItemId(MenuItem itemId) {
        this.itemId = itemId;
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
        if (!(object instanceof MenuItemIngredient)) {
            return false;
        }
        MenuItemIngredient other = (MenuItemIngredient) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rms.entity.MenuItemIngredient[ id=" + id + " ]";
    }
    
}
