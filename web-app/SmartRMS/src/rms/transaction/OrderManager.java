/*
 * Copyright (c) 2010, Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software
 * except in compliance with the terms of the license at:
 * http://developer.sun.com/berkeley_license.html
 */

package rms.transaction;

import rms.entity.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
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
    
    
    
    public int placeOrder(String custName, String orderedBy, List<Container> item_list) {

        try {
            CustomerOrder order = addOrder(custName,orderedBy);
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
        //order.setOrderNo(1046);
        order.setCustName(cutomerName);
        order.setOrderedBy(orderedBy);
        order.setTableNo(0);
        order.setAccepted((short)0);


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

    public Map getOrderDetails(int orderId) {

        Map orderMap = new HashMap();

        // get order
        CustomerOrder order = customerOrderFacade.find(orderId);

        // get customer
//        Customer customer = order.getCustomer();
//
//        // get all ordered products
//        List<OrderedProduct> orderedProducts = orderedProductFacade.findByOrderId(orderId);
//
//        // get product details for ordered items
//        List<Product> products = new ArrayList<Product>();
//
//        for (OrderedProduct op : orderedProducts) {
//
//            Product p = (Product) productFacade.find(op.getOrderedProductPK().getProductId());
//            products.add(p);
//        }
//
//        // add each item to orderMap
//        orderMap.put("orderRecord", order);
//        orderMap.put("customer", customer);
//        orderMap.put("orderedProducts", orderedProducts);
//        orderMap.put("products", products);

        return orderMap;
    }

}