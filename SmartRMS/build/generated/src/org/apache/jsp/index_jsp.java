package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html\n");
      out.write("    xmlns=\"http://www.w3.org/1999/xhtml\"  \n");
      out.write("    xmlns:h=\"http://xmlns.jcp.org/jsf/html\"\n");
      out.write("    xmlns:f=\"http://java.sun.com/jsf/core\"  \n");
      out.write("    xmlns:p=\"http://primefaces.org/ui\">\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Smart RMS - Staff Login</title>\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"mystyle.css\">\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    <body>\n");
      out.write("        <script type=\"text/javascript\">\n");
      out.write("            function ValidateUsename()\n");
      out.write("            {\n");
      out.write("                if (document.getElementById(\"username\").value == \"\")\n");
      out.write("                {\n");
      out.write("                    document.getElementById(\"username\").style.borderColor = \"red\";\n");
      out.write("                } else\n");
      out.write("                {\n");
      out.write("                    document.getElementById(\"username\").style.borderColor = \"#cacaca\";\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("            function ValidatePassword()\n");
      out.write("            {\n");
      out.write("                if (document.getElementById(\"password\").value == \"\")\n");
      out.write("                {\n");
      out.write("                    document.getElementById(\"password\").style.borderColor = \"red\";\n");
      out.write("                } else\n");
      out.write("                {\n");
      out.write("                    document.getElementById(\"password\").style.borderColor = \"#cacaca\";\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("        <div id=\"header\">\n");
      out.write("\n");
      out.write("            <div class = \"banner\">\n");
      out.write("                <b>Smart RMS</b>\n");
      out.write("                <div class = \"desc\">Restaurant Management System</div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div class = \"date\">\n");
      out.write("                <p id=\"demo\"></p>\n");
      out.write("\n");
      out.write("                <script>\n");
      out.write("                    var d = new Date();\n");
      out.write("                    document.getElementById(\"demo\").innerHTML = d.toDateString();\n");
      out.write("                </script>\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div id=\"nav\">\n");
      out.write("            <b>Login for...</b><br>\n");
      out.write("            Receptionist<br>\n");
      out.write("            Cashier<br>\n");
      out.write("            Chef<br>\n");
      out.write("            Head Chef<br>\n");
      out.write("            Manager<br>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div id=\"section\">\n");
      out.write("\n");
      out.write("\n");
      out.write("            <div class=\"please\">\n");
      out.write("                <big><b>Please login to access your workspace</b></big> <br> <br>\n");
      out.write("            </div>\n");
      out.write("            <div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                <!--            <form action=\"ValidateLogin1\" method=\"POST\" name=\"AdminLogin\">-->\n");
      out.write("                <form action=\"ValidateLogin1\" method=\"POST\" name=\"AdminLogin\">\n");
      out.write("                    <table align = \"center\">\n");
      out.write("                        <tr>\n");
      out.write("                            <td align = \"right\">Username: </td> \n");
      out.write("                            <td><input type=\"text\" id=\"username\" name=\"username\" onblur=\"ValidateUsename();\" placeholder=\"Username\"><br></td>\n");
      out.write("                        </tr>\n");
      out.write("\n");
      out.write("                        <tr>\n");
      out.write("                            <td align = \"right\">Password: </td>\n");
      out.write("                            <td><input type=\"password\" id=\"password\" name=\"password\" onblur=\"ValidatePassword();\" placeholder=\"Password\"><br></td>\n");
      out.write("                        </tr>\n");
      out.write("\n");
      out.write("                        <tr>\n");
      out.write("                            <td align = \"center\"><!-- <input class = \"myButton\" type=\"submit\" value=\"LogIn\"> --><br></td>\n");
      out.write("                        </tr>\n");
      out.write("                    </table>\n");
      out.write("                    <input class = \"myButton\" type=\"submit\" value=\"LogIn\">\n");
      out.write("                </form>\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("            <p:button value=\"Bookmark\" icon=\"ui-icon-star\">\n");
      out.write("                    <f:param name=\"productId\" value=\"10\" />\n");
      out.write("                </p:button>\n");
      out.write("\n");
      out.write("\n");
      out.write("            <br>\n");
      out.write("            <!--<button class = \"myButton\" type=\"button\" onclick=\"alert('Hello world!')\">Login</button>-->\n");
      out.write("\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div id=\"footer\">\n");
      out.write("            Copyright © 2016 UCSC 2nd Year SD Project Group #12\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
