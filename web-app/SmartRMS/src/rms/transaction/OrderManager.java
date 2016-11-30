/*
 * Copyright (c) 2010, Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software
 * except in compliance with the terms of the license at:
 * http://developer.sun.com/berkeley_license.html
 */
package rms.transaction;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import rms.entity.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import rms.db.DBpack;
import rms.session.CustomerOrderFacade;
import rms.session.OrderItemFacade;
import rms.view.Container;

/**
 *
 * @author tgiunipero
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class OrderManager {

    @PersistenceContext(unitName = "SmartRMSPU")
    private EntityManager em;
    @Resource
    private SessionContext context;

    @EJB
    private CustomerOrderFacade customerOrderFacade;
    @EJB
    private OrderItemFacade orderItemFacade;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)

    public void refresh(List<CustomerOrder> list) {
        for (CustomerOrder i : list) {
            em.refresh(i);
        }
    }

    public void acceptOrder(CustomerOrder order) {
        if (order.getStatus().equals((short) 1)) {
            //List<OrderItem> orderItems = orderItemFacade.findAll();

//            for (OrderItem orderItem : orderItems) {
//                if (Objects.equals(orderItem.getOrderNo().getOrderNo(), order.getOrderNo())) {
//                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Info!", orderItem.getItemId().getItemName()));
//                }
//            }
            List<OrderItem> orderItems = (List<OrderItem>) order.getOrderItemCollection();
            for (OrderItem orderItem : orderItems) {
                //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Info!", orderItem.getItemId().getItemName()));
                List<MenuItemIngredient> menuItemIngredients = (List<MenuItemIngredient>)orderItem.getItemId().getMenuItemIngredientCollection();
                for (MenuItemIngredient menuItemIngredient : menuItemIngredients) {
                    Ingredient ingredient = menuItemIngredient.getIngredientId();
                    BigDecimal consumption = menuItemIngredient.getAmount().multiply(BigDecimal.valueOf(orderItem.getQuantity()));
                    ingredient.setAmount(ingredient.getAmount().subtract(consumption));
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ingredient.getName()+" Consumed!",
                    ingredient.getAmount().toString() + ingredient.getUnit()+" Left"));
                    em.flush();
                    em.merge(ingredient);
                    em.flush();
                    
                    IngredientConsumption ingredientConsumption = new IngredientConsumption();
                    ingredientConsumption.setAction("CONSUME");
                    ingredientConsumption.setIngredientId(ingredient);
                    ingredientConsumption.setAmount(consumption);
                    
                    em.persist(ingredientConsumption);
                    em.flush();
                }
            }
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error!", "Customer name cannot be empty!"));
        }
        em.flush();
        em.merge(order);
        em.flush();
    }

    public int placeOrder(String custName, String orderedBy, List<Container> item_list) {

        try {
            CustomerOrder order = addOrder(custName, orderedBy);
            addOrderedItems(order, item_list);
            return order.getOrderNo();
        } catch (Exception e) {
            context.setRollbackOnly();
            return 0;
        }
    }

    private CustomerOrder addOrder(String cutomerName, String orderedBy) {

        // set up customer order
        CustomerOrder order = new CustomerOrder();
        order.setCustName(cutomerName);
        order.setOrderedBy(orderedBy);
        order.setTableNo(0);
        order.setStatus((short) 0);

        em.persist(order);
        return order;
    }

    private void addOrderedItems(CustomerOrder order, List<Container> itemList) {

        em.flush();
        // iterate through ordered list
        for (Container cItem : itemList) {

            OrderItem orderedItem = cItem.getOrderItem();
            orderedItem.setOrderNo(order);

            em.persist(orderedItem);
        }
    }

}
