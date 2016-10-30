/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import rms.db.DBpack;
import rms.entity.DiningTable;
import rms.session.DiningTableFacade;

/**
 *
 * @author Tharinda
 */
public class ReserveHandler extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        ResultSet res,res1;
        DiningTableFacade diningTableFacade = new DiningTableFacade();
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            List<Integer> tableList = new ArrayList<>();
            
            HttpSession session = request.getSession();
            String custName = (String) session.getAttribute("cust_name");
            String resDate = (String) session.getAttribute("res_date");
            String resTime = (String) session.getAttribute("res_time");
            String resList = "";
            String query = "SELECT reservation.res_id, reservation_table.res_tb_id, reservation_table.table_id "
                    + "FROM `reservation` "
                    + "INNER JOIN reservation_table ON reservation.res_id = reservation_table.res_id "
                    + "WHERE date = '"+resDate+"' AND meal_time = '"+resTime+"'";
            res = DBpack.getResult(query);
            
//            String query1 = "SELECT * FROM dining_table WHERE table_type = 'RES'";
//            res1 = DBpack.getResult(query1);
//            if (res1.next()){
//                out.println(res1.getString("table_no"));
//                while (res1.next()){
//                    out.println(res1.getString("table_no"));
//                }
//            }
            if (res.next()){
                //out.println(res.getString("reservation_table.res_tb_id"));
                resList = res.getString("reservation_table.res_tb_id");
                tableList.add(res.getInt("reservation_table.table_id"));
                while (res.next()){
                    tableList.add(res.getInt("reservation_table.table_id"));
                }
            }
            else{
                //out.println(resTime);
                //out.println("Empty");
                resList = "Empty";
            }
                session.setAttribute("reslist", tableList);
                request.getRequestDispatcher("users/receptionist/reserve-interface.xhtml").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ReserveHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ReserveHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
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
