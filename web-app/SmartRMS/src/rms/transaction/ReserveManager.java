/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rms.transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import rms.entity.Customer;
import rms.entity.DiningTable;
import rms.entity.Reservation;
import rms.entity.ReservationTable;

/**
 *
 * @author Tharinda
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ReserveManager {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "SmartRMSPU")
    private EntityManager em;
    @Resource
    private SessionContext context;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    
    public Customer createCustomer(String name){
        Customer customer = new Customer();
        customer.setName(name);
        em.persist(customer);
        return customer;
    }
    
    public Reservation addReservation(Customer customer, String username, String date, String time) throws ParseException{
        Reservation reservation = new Reservation();
        reservation.setDate((Date) new SimpleDateFormat("yyyy-MM-dd").parse(date));
        reservation.setMealTime(time);
        reservation.setCustomerId(customer);
        reservation.setPlacedBy(username);
        em.persist(reservation);
        return reservation;
    }
    
    public void addTables(Reservation reservation, List<DiningTable> tables){
        em.flush();
        for (DiningTable table : tables){
            ReservationTable tempTable = new ReservationTable();
            tempTable.setResId(reservation);
            tempTable.setTableId(table);
            em.persist(tempTable);
        }
    }
    
    public int reserve(Customer customer, String username, String date, String time, List<DiningTable> tables ){
        try {
            Reservation reservation = addReservation(customer, username, date, time);
            addTables(reservation, tables);
            return reservation.getResId();
        } catch (Exception e) {
            e.printStackTrace();
            context.setRollbackOnly();
            return 0;
        }
    }
    
}
