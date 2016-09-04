/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rms.test;

import java.io.IOException;
import java.io.PrintWriter;

import rms.view.ChefView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import rms.common.ComTainer;

/**
 *
 * @author Tharinda
 */
public class AccountDispatch extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
//    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        PrintWriter out = response.getWriter();
//
//        String uType = (String) request.getAttribute("type");
//        String fName = (String) request.getAttribute("firstname");
//        String lName = (String) request.getAttribute("lastname");
//
//        request.setAttribute("type", uType);
//        request.setAttribute("firstname", fName);
//        request.setAttribute("lastname", lName);
//        
//        //out.write("<strong>error 2nd page " + uType + fName + lName + " heh</strong>");
//        switch (uType) {
//            case "CHEF":
//                ComTainer.render = true;
//                request.getRequestDispatcher("users/chef/chef.xhtml").forward(request, response);
//                break;
//            case "RECEPTIONIST":
//                getServletContext().getRequestDispatcher("/WEB-INF/users/receptionist/receptionist.jsp").forward(request, response);
//                break;
//            case "CASHIER":
//                getServletContext().getRequestDispatcher("/WEB-INF/cashier.jsp").forward(request, response);
//                break;
//            default:
//                out.write("<br><strong>strings dont match wtf</strong>");
//                break;
//        }
//
//    }

}
