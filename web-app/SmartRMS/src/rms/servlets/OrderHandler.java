/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rms.servlets;

import rms.db.DBpack;
import java.sql.*;
import java.util.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.ejb.EJB;
import javax.enterprise.inject.spi.Bean;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import rms.transaction.OrderManager;
import rms.view.Container;
import rms.view.RecepView;

/**
 *
 * @author Tharinda
 */
public class OrderHandler extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB
    private OrderManager orderManager;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String userPath = request.getServletPath();
        HttpSession session = request.getSession();
        
        String custName = (String) session.getAttribute("cust_name");
        String firstname = (String) session.getAttribute("firstname");
        String lastname = (String) session.getAttribute("lastname");
        String type = (String) session.getAttribute("type");
        String username = (String) session.getAttribute("username");
        int auth_id = (int) session.getAttribute("auth_id");
        
        if (userPath.equals("/NewOrder")) {
                session.invalidate();
                session = request.getSession(true);
                session.setAttribute("firstname", firstname);
                session.setAttribute("lastname", lastname);
                session.setAttribute("type", type);
                session.setAttribute("username", username);
                session.setAttribute("auth_id", auth_id);

                request.getRequestDispatcher("users/receptionist/receptionist_2.xhtml").forward(request, response);
        }

        else if (userPath.equals("/PlaceOrder")) {
            
            List<Container> item_list = (List<Container>) session.getAttribute("item_list");

            int orderId = orderManager.placeOrder(custName, username, item_list);

            if (orderId != 0) {

                // otherwise, send back to checkout page and display error

                request.setAttribute("message", "Order Successful!");
                request.setAttribute("orderno", orderId);
                request.getRequestDispatcher("users/receptionist/order-feedback.xhtml").forward(request, response);

            } else {
                request.setAttribute("message", "Order Failed!");
                request.getRequestDispatcher("users/receptionist/order-feedback.xhtml").forward(request, response);

            }

        } else {

            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */

                //String custName = (String) request.getParameter("cust_name");
                custName = (String) request.getSession().getAttribute("cust_name");

                try {
                    String query = "INSERT INTO `smart_rms`.`order` (`table_no`, `cust_name`) VALUES  (0, '" + custName + "');";
                    DBpack.runUpdate(query);

                    getServletContext().getRequestDispatcher("/WEB-INF/users/receptionist/receptionist.jsp").forward(request, response);

                } catch (Exception e) {
                    e.printStackTrace();
                    out.write("<strong>Database Connection Error: Could not connect to database</strong><br>");
                    out.write("<strong>Order: " + custName + "</strong>");
                }

            }
        }

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
