/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rms.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import rms.common.ComTainer;
import rms.db.DBpack;
import rms.entity.UserAccount;
import rms.session.UserAccountFacade;

/**
 *
 * @author Tharinda
 */
@WebServlet(loadOnStartup = 1)
public class LoginServlet extends HttpServlet {

    ResultSet res;

    String username, password, query;

    @EJB
    private UserAccountFacade userAccountFacade;
    

    @Override
    public void init() throws ServletException {

        // store user list in servlet context
        List<UserAccount> items;
        items = userAccountFacade.findAll();
        getServletContext().setAttribute("users", items);
        
        

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String userPath = request.getServletPath();

        response.setContentType("text/html;charset=UTF-8");

        if (userPath.equals("/Logout")) {
            session = request.getSession();
            session.invalidate();   // terminate session
            response.sendRedirect("/SamrtRMS/");
            return;
        }

        try {
            username = request.getParameter("username");
            password = request.getParameter("password");
            query = "select * from user_account where username = '" + username + "' and password = '" + password + "'";
            res = DBpack.getResult(query);
            if (res.next()) {
//                request.setAttribute("firstname", res.getString("f_name"));
//                request.setAttribute("lastname", res.getString("l_name"));
//                request.setAttribute("type", res.getString("user_type"));

                String uType = res.getString("user_type");
                Random generator = new Random();
                int i = generator.nextInt(10000) + 1;

                session.setAttribute("firstname", res.getString("f_name"));
                session.setAttribute("lastname", res.getString("l_name"));
                session.setAttribute("type", res.getString("user_type"));
                session.setAttribute("username", username);
                session.setAttribute("auth_id", i);
                ComTainer.setKey(username, i);
                switch (uType) {
                    case "CHEF":
                        request.getRequestDispatcher("users/chef/chef.xhtml").forward(request, response);
                        break;
                    case "RECEPTIONIST":
                        //request.getRequestDispatcher("/WEB-INF/users/receptionist/receptionist.jsp").forward(request, response);
                        request.getRequestDispatcher("users/receptionist/receptionist_2.xhtml").forward(request, response);
                        break;
                    case "CASHIER":
                        //getServletContext().getRequestDispatcher("/WEB-INF/cashier.jsp").forward(request, response);
                        break;
                    default:
                        request.setAttribute("message", "No Interface For User Type");
                        request.getRequestDispatcher("/common/error.jsp").forward(request, response);
                        break;
                }

            } else {
                request.setAttribute("message", "Invalid Login");
                getServletContext().getRequestDispatcher("/common/error.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Could Not Connect To Database");
            getServletContext().getRequestDispatcher("/common/error.jsp").forward(request, response);
        } finally {

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
