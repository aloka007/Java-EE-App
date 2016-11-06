/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rms.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import rms.entity.Reservation;

/**
 *
 * @author Tharinda
 */
@Stateless
public class ReservationFacade extends AbstractFacade<Reservation> {

    @PersistenceContext(unitName = "SmartRMSPU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public ReservationFacade() {
        super(Reservation.class);
    }
    
}
