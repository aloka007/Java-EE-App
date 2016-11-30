/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rms.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Tharinda
 */
@Entity
@Table(name = "ingredient")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ingredient.findAll", query = "SELECT i FROM Ingredient i"),
    @NamedQuery(name = "Ingredient.findById", query = "SELECT i FROM Ingredient i WHERE i.id = :id"),
    @NamedQuery(name = "Ingredient.findByName", query = "SELECT i FROM Ingredient i WHERE i.name = :name"),
    @NamedQuery(name = "Ingredient.findByDescription", query = "SELECT i FROM Ingredient i WHERE i.description = :description"),
    @NamedQuery(name = "Ingredient.findByUnit", query = "SELECT i FROM Ingredient i WHERE i.unit = :unit"),
    @NamedQuery(name = "Ingredient.findByAmount", query = "SELECT i FROM Ingredient i WHERE i.amount = :amount"),
    @NamedQuery(name = "Ingredient.findByWarn", query = "SELECT i FROM Ingredient i WHERE i.warn = :warn"),
    @NamedQuery(name = "Ingredient.findByCrit", query = "SELECT i FROM Ingredient i WHERE i.crit = :crit")})
public class Ingredient implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "name")
    private String name;
    @Size(max = 50)
    @Column(name = "description")
    private String description;
    @Size(max = 4)
    @Column(name = "unit")
    private String unit;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "warn")
    private BigDecimal warn;
    @Column(name = "crit")
    private BigDecimal crit;
    @OneToMany(mappedBy = "ingredientId")
    private Collection<IngredientConsumption> ingredientConsumptionCollection;
    @OneToMany(mappedBy = "ingredientId")
    private Collection<MenuItemIngredient> menuItemIngredientCollection;

    public Ingredient() {
    }

    public Ingredient(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getWarn() {
        return warn;
    }

    public void setWarn(BigDecimal warn) {
        this.warn = warn;
    }

    public BigDecimal getCrit() {
        return crit;
    }

    public void setCrit(BigDecimal crit) {
        this.crit = crit;
    }

    @XmlTransient
    public Collection<IngredientConsumption> getIngredientConsumptionCollection() {
        return ingredientConsumptionCollection;
    }

    public void setIngredientConsumptionCollection(Collection<IngredientConsumption> ingredientConsumptionCollection) {
        this.ingredientConsumptionCollection = ingredientConsumptionCollection;
    }

    @XmlTransient
    public Collection<MenuItemIngredient> getMenuItemIngredientCollection() {
        return menuItemIngredientCollection;
    }

    public void setMenuItemIngredientCollection(Collection<MenuItemIngredient> menuItemIngredientCollection) {
        this.menuItemIngredientCollection = menuItemIngredientCollection;
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
        if (!(object instanceof Ingredient)) {
            return false;
        }
        Ingredient other = (Ingredient) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rms.entity.Ingredient[ id=" + id + " ]";
    }
    
}
