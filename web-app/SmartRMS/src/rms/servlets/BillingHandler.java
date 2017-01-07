/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import rms.db.DBpack;
import rms.entity.Bill;
import rms.entity.BillOrder;
import rms.entity.BillOrderItem;
import rms.entity.CustomerOrder;
import rms.transaction.OrderManager;

/**
 *
 * @author Tharinda
 */
public class BillingHandler extends HttpServlet {
    
    @EJB
    private OrderManager orderManager;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        
        Bill bill = (Bill)session.getAttribute("bill");
        for (BillOrder order : bill.getOrders()) {
            order.getCustomerOrder().setStatus((short) 4);
            orderManager.acceptOrder(order.getCustomerOrder());
            
        }
        try {
            int tempInt = DBpack.runUpdate("INSERT INTO `smart_rms`.`bill` (`waiter_id`, `cashier_id`, `customer_name`, `amount`, `discount`, `tax`, `sub_total`, `tip`, `total`, `pay_mode`) VALUES ('"+
                    bill.getWaiterId()+"', '"+
                    bill.getCashierId()+"', '"+
                    bill.getCustomerName()+"', "+
                    bill.getAmount()+", "+
                    bill.getDiscount()+", "+
                    bill.getTax()+", "+
                    bill.getSubTotal()+", "+
                    bill.getTip()+", "+
                    bill.getTotal()+", '"+
                    bill.getPayMode()+"')");
            for (BillOrder billOrder : bill.getOrders()) {
                int tempInt2 = DBpack.runUpdate("INSERT INTO `smart_rms`.`bill_order` (`bill_id`, `order_no`) VALUES ("+
                        tempInt+", "+
                        billOrder.getCustomerOrder().getOrderNo()+")");
                for (BillOrderItem billOrderItem : billOrder.getItems()) {
                    DBpack.runUpdate("INSERT INTO `smart_rms`.`bill_order_item` (`bill_order_id`, `item_name`, `quantity`, `price`, `amount`) VALUES ("+
                            tempInt2+", '"+
                            billOrderItem.getOrderItem().getItemId().getItemName()+"', "+
                            billOrderItem.getOrderItem().getQuantity()+", "+
                            BigDecimal.valueOf(billOrderItem.getOrderItem().getItemId().getPrice()) +", "+
                            billOrderItem.getAmount()+");");
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(BillingHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        String firstname = (String) session.getAttribute("firstname");
        String lastname = (String) session.getAttribute("lastname");
        String type = (String) session.getAttribute("type");
        String username = (String) session.getAttribute("username");
        int auth_id = (int) session.getAttribute("auth_id");

        session.invalidate();
        session = request.getSession(true);
        session.setAttribute("firstname", firstname);
        session.setAttribute("lastname", lastname);
        session.setAttribute("type", type);
        session.setAttribute("username", username);
        session.setAttribute("auth_id", auth_id);

        request.getRequestDispatcher("/users/cashier/cashier.xhtml").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
