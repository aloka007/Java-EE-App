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
@Table(name = "dining_table")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DiningTable.findAll", query = "SELECT d FROM DiningTable d"),
    @NamedQuery(name = "DiningTable.findById", query = "SELECT d FROM DiningTable d WHERE d.id = :id"),
    @NamedQuery(name = "DiningTable.findByTableNo", query = "SELECT d FROM DiningTable d WHERE d.tableNo = :tableNo"),
    @NamedQuery(name = "DiningTable.findByTableType", query = "SELECT d FROM DiningTable d WHERE d.tableType = :tableType"),
    @NamedQuery(name = "DiningTable.findByNumOfSeats", query = "SELECT d FROM DiningTable d WHERE d.numOfSeats = :numOfSeats")})
public class DiningTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "table_no")
    private int tableNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "table_type")
    private String tableType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_of_seats")
    private int numOfSeats;

    public DiningTable() {
    }

    public DiningTable(Integer id) {
        this.id = id;
    }

    public DiningTable(Integer id, int tableNo, String tableType, int numOfSeats) {
        this.id = id;
        this.tableNo = tableNo;
        this.tableType = tableType;
        this.numOfSeats = numOfSeats;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTableNo() {
        return tableNo;
    }

    public void setTableNo(int tableNo) {
        this.tableNo = tableNo;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
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
        if (!(object instanceof DiningTable)) {
            return false;
        }
        DiningTable other = (DiningTable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rms.entity.DiningTable[ id=" + id + " ]";
    }
    
}
