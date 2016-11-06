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
@Table(name = "reservation_table")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReservationTable.findAll", query = "SELECT r FROM ReservationTable r"),
    @NamedQuery(name = "ReservationTable.findByResTbId", query = "SELECT r FROM ReservationTable r WHERE r.resTbId = :resTbId")})
public class ReservationTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "res_tb_id")
    private Integer resTbId;
    @JoinColumn(name = "table_id", referencedColumnName = "id")
    @ManyToOne
    private DiningTable tableId;
    @JoinColumn(name = "res_id", referencedColumnName = "res_id")
    @ManyToOne
    private Reservation resId;

    public ReservationTable() {
    }

    public ReservationTable(Integer resTbId) {
        this.resTbId = resTbId;
    }

    public Integer getResTbId() {
        return resTbId;
    }

    public void setResTbId(Integer resTbId) {
        this.resTbId = resTbId;
    }

    public DiningTable getTableId() {
        return tableId;
    }

    public void setTableId(DiningTable tableId) {
        this.tableId = tableId;
    }

    public Reservation getResId() {
        return resId;
    }

    public void setResId(Reservation resId) {
        this.resId = resId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (resTbId != null ? resTbId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservationTable)) {
            return false;
        }
        ReservationTable other = (ReservationTable) object;
        if ((this.resTbId == null && other.resTbId != null) || (this.resTbId != null && !this.resTbId.equals(other.resTbId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rms.entity.ReservationTable[ resTbId=" + resTbId + " ]";
    }
    
}
