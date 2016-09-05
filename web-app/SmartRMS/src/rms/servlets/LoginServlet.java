/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rms.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import rms.security.MdFive;
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

        // store user list in servlet context            <<<<<<<< not necessary- to be removed
        List<UserAccount> items;
        items = userAccountFacade.findAll();
        getServletContext().setAttribute("users", items);
        
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {  // the only servlet method used for the app

        HttpSession session = request.getSession(); // get session from request
        
        String userPath = request.getServletPath(); // get the url pattern the user came from

        response.setContentType("text/html;charset=UTF-8");  // set encoding of response

        if (userPath.equals("/Logout")) {  // logout procedure
            session = request.getSession(); // get the session from request
            session.invalidate();   // terminate session. this also destroys the beans and attributes associated with the session
            response.sendRedirect("/SamrtRMS/"); //rediredt user back into login screen
            return;
        }

        try {
            username = request.getParameter("username");  // get data from request parameters
            password = request.getParameter("password");
            password = MdFive.hash(username); // hash the password with MD5 hashing
            
            //The database query- this part is to be replaced later by JPA which eliminates the need to use database connectors
            query = "select * from user_account where username = '" + username + "' and password = '" + password + "'";
            res = DBpack.getResult(query);
            if (res.next()) {  //this precedure is a very primitive one which entirely depends on the database
                //this will later be replaced by JPA
                
//                request.setAttribute("firstname", res.getString("f_name"));
//                request.setAttribute("lastname", res.getString("l_name"));
//                request.setAttribute("type", res.getString("user_type"));

                String uType = res.getString("user_type"); // used to restrict users from accessing other user interfaces
                Random generator = new Random();           // an informal way of securing the app
                int i = generator.nextInt(10000) + 1;      //a random number is generated once the user is verified

                session.setAttribute("firstname", res.getString("f_name"));
                session.setAttribute("lastname", res.getString("l_name"));
                session.setAttribute("type", res.getString("user_type"));
                session.setAttribute("username", username);
                session.setAttribute("auth_id", i); // one copy goes along with the session
                ComTainer.setKey(username, i);  // another copy stored put into a hashmap with username as the key
                switch (uType) {   // this switch seperates and sends various users to their respective interfaces
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
                request.setAttribute("message", "Invalid Login");   // invalid login code
                getServletContext().getRequestDispatcher("/common/error.jsp").forward(request, response);
            }
        } catch (SQLException | ServletException | IOException e) { // database error code
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
