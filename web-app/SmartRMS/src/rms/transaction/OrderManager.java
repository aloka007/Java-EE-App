/*
 * Copyright (c) 2010, Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software
 * except in compliance with the terms of the license at:
 * http://developer.sun.com/berkeley_license.html
 */
package rms.transaction;

import java.math.BigDecimal;
import rms.entity.*;
import java.util.List;
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
            List<OrderItem> orderItems = (List<OrderItem>) order.getOrderItemCollection();
            for (OrderItem orderItem : orderItems) {//for each item in order
                List<MenuItemIngredient> menuItemIngredients = (List<MenuItemIngredient>)orderItem.getItemId().getMenuItemIngredientCollection();//get the ingredients for a menu item
                for (MenuItemIngredient menuItemIngredient : menuItemIngredients) {//for each ingredient of menu item
                    Ingredient ingredient = menuItemIngredient.getIngredientId();//get its ingredient
                    BigDecimal consumption = menuItemIngredient.getAmount().multiply(BigDecimal.valueOf(orderItem.getQuantity()));//get the consumed amount
                    ingredient.setAmount(ingredient.getAmount().subtract(consumption));//set the ingredient's amt
                    if(1==ingredient.getAmount().compareTo(ingredient.getWarn())){
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ingredient.getName()+" Consumed!",
                                    ingredient.getAmount().toString() + ingredient.getUnit()+" Remaining"));//display a message with details
                    }
                    else{
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ingredient.getName()+" Consumed!",
                                    "Only "+ingredient.getAmount().toString() + ingredient.getUnit()+" Remaining"));//display a message with details
                    }
                    em.flush();
                    em.merge(ingredient);//save the updated ingredient to database
                    em.flush();
                    
                    IngredientConsumption ingredientConsumption = new IngredientConsumption();//create a new ing.consumption object
                    ingredientConsumption.setAction("CONSUME");
                    ingredientConsumption.setIngredientId(ingredient);
                    ingredientConsumption.setAmount(consumption);
                    
                    em.persist(ingredientConsumption);
                    em.flush();
                }
            }
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
            context.setRollbackOnly();//jpa rollback
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
    
    public void saveIngredient(Ingredient ingredient){
        em.flush();
        em.merge(ingredient);
        em.flush();
    }
    
    public void saveNewRule(MenuItemIngredient rule){
        em.flush();
        if (rule.getId() == null) {
            em.persist(rule);
        }
        else{
            em.merge(rule);
        }
        em.flush();
    }
    
    public void insertMenuItem(MenuItem item){
        em.flush();
        em.persist(item);
        em.flush();
    }
    
    public void insertIngredient(Ingredient item){
        em.flush();
        em.persist(item);
        em.flush();
    }
    

}
