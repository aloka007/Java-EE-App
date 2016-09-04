<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : chef
    Created on : 16-Aug-2016, 21:35:06
    Author     : Tharinda
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Smart RMS - Staff Login</title>
        <link rel="stylesheet" type="text/css" href="mystyle.css">

    </head>

    <body>

        <%@include file="/WEB-INF/users/common/header.html"%>

        <div id="nav">

        </div>

        <div id="section">

            <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>

            <script type="text/javascript">
                function loadlink() {
                    $('#please').load('/WEB-INF/users/common/order_table.html', function () {
                        $(this).unwrap();
                    });
                }

                loadlink(); // This will run on page load
                setInterval(function () {
                    loadlink() // this will run after every 5 seconds
                }, 1000);

            </script>


            <div class="please">
                <div id="placedDiv">
                    <sql:query var="order_result" dataSource="jdbc/smart-rms">
                        SELECT order_no, order_time, table_no, cust_name FROM `order`
                    </sql:query>

                    <table border="1">
                        <!-- column headers -->
                        <tr>
                            <c:forEach var="columnName" items="${order_result.columnNames}">
                                <th><c:out value="${columnName}"/></th>
                                </c:forEach>
                        </tr>
                        <!-- column data -->
                        <c:forEach var="row" items="${order_result.rowsByIndex}">
                            <tr>
                                <c:forEach var="column" items="${row}">
                                    <td><c:out value="${column}"/></td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </table>
                </div>

            </div>





        </div>



        <!--        <div id="footer">
                    Copyright © 2016 UCSC 2nd Year SD Project Group #12
                </div>-->


    </body>
</html>

