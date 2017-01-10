/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rms.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import rms.entity.MenuItemIngredient;

/**
 *
 * @author Tharinda
 */
@Stateless
public class MenuItemIngredientFacade extends AbstractFacade<MenuItemIngredient> {

    @PersistenceContext(unitName = "SmartRMSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenuItemIngredientFacade() {
        super(MenuItemIngredient.class);
    }
    
}
