/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rms.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tharinda
 */
@Entity
@Table(name = "menu_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MenuItem.findAll", query = "SELECT m FROM MenuItem m"),
    @NamedQuery(name = "MenuItem.findByItemId", query = "SELECT m FROM MenuItem m WHERE m.itemId = :itemId"),
    @NamedQuery(name = "MenuItem.findByMenuIndex", query = "SELECT m FROM MenuItem m WHERE m.menuIndex = :menuIndex"),
    @NamedQuery(name = "MenuItem.findByItemType", query = "SELECT m FROM MenuItem m WHERE m.itemType = :itemType"),
    @NamedQuery(name = "MenuItem.findByItemName", query = "SELECT m FROM MenuItem m WHERE m.itemName = :itemName"),
    @NamedQuery(name = "MenuItem.findByPrice", query = "SELECT m FROM MenuItem m WHERE m.price = :price"),
    @NamedQuery(name = "MenuItem.findBySpiciness", query = "SELECT m FROM MenuItem m WHERE m.spiciness = :spiciness")})
public class MenuItem implements Serializable, Comparable{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "item_id")
    private Integer itemId;
    @Column(name = "menu_index")
    private Integer menuIndex;
    @Size(max = 50)
    @Column(name = "item_type")
    private String itemType;
    @Size(max = 50)
    @Column(name = "item_name")
    private String itemName;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private Float price;
    @Column(name = "spiciness")
    private Short spiciness;
    

    public MenuItem() {
    }

    public MenuItem(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getMenuIndex() {
        return menuIndex;
    }

    public void setMenuIndex(Integer menuIndex) {
        this.menuIndex = menuIndex;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Short getSpiciness() {
        return spiciness;
    }

    public void setSpiciness(Short spiciness) {
        this.spiciness = spiciness;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemId != null ? itemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenuItem)) {
            return false;
        }
        MenuItem other = (MenuItem) object;
        if ((this.itemId == null && other.itemId != null) || (this.itemId != null && !this.itemId.equals(other.itemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rms.entity.MenuItem[ itemId=" + itemId + " ]";
    }
    
    @Override
    public int compareTo(Object i){
        MenuItem j = (MenuItem) i;
        return (this.menuIndex - j.getMenuIndex());
    }
}
    

