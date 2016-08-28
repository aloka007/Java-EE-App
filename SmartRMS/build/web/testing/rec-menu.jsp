<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : chef
    Created on : 16-Aug-2016, 21:35:06
    Author     : Tharinda
--%>



<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Smart RMS - Receptionist</title>
        <link rel="stylesheet" type="text/css" href="mystyle.css">
    </head>

    <body>
<!--        header include-->
        <%@include file="/WEB-INF/users/common/header.html"%>

        <div id="nav">
            <a href="rec-menu.jsp"><b><font color="black">View Menu</font></b></a>
        </div>
            
        <div id="section">


<!--            <div class="please">
                <big><b>This is Receptionist's Workspace<br>
                        You Are:  <font color="red">${firstname} ${lastname}</font> </b></big>
            </div>-->

            <sql:query var="result" dataSource="jdbc/smart-rms">
                SELECT item_id, item_type, item_name, description, price, spicyness FROM menu_item
            </sql:query>
            <br>A test view of the menu...<br>
            <table border="1">
                <!-- column headers -->
                <tr>
                    <c:forEach var="columnName" items="${result.columnNames}">
                        <th><c:out value="${columnName}"/></th>
                        </c:forEach>
                </tr>
                <!-- column data -->
                <c:forEach var="row" items="${result.rowsByIndex}">
                    <tr>
                        <c:forEach var="column" items="${row}">
                            <td><c:out value="${column}"/></td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </table>






        </div>


        <!--        <div id="footer">
                    Copyright © 2016 UCSC 2nd Year SD Project Group #12
                </div>-->


    </body>
</html>
