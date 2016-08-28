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
        <link rel="stylesheet" type="text/css" href="resources/css/mystyle.css">
    </head>

    <body>
        <!--        header include-->
        <%@include file="/WEB-INF/users/common/header.html"%>

        <div id="nav">
            <input type="button"  name="show-menu" value="View Menu" onclick="switchDiv('menuDiv')" />
            <input type="button"  name="show-order" value="Order" onclick="switchDiv('orderDiv')" />
            <input type="button"  name="show-order" value="See Orders" onclick="switchDiv('placedDiv')" />
        </div>

        <div id="section">

            <!--            Script to switch the tabs-->
            <script>
                var divlist = ["menuDiv", "orderDiv","placedDiv"];
                function switchDiv(name) {
                    for (i = 0; i < divlist.length; i++) {
                        document.getElementById(divlist[i]).style.display = "none";
                    }
                    document.getElementById(name).style.display = "block";
                }
            </script>

            <!--            Menu Section-->
            <div id="menuDiv"  style="display:none;">
                <sql:query var="result" dataSource="jdbc/smart-rms">
                    SELECT item_id, item_type, item_name, description, price, spiciness FROM menu_item
                </sql:query>
                <br>A test view of the menu...<br>
                <table>
                    <!-- column headers -->
                    <tr class = "shadedth">
                        <th>Item ID</th>
                        <th>Type</th>
                        <th>Item Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Spiciness</th>
                    </tr>
                    <!-- column data -->
                    <c:forEach var="row" items="${result.rowsByIndex}">
                        <tr class="shadedtr">
                            <c:forEach var="column" items="${row}">
                                <td><c:out value="${column}"/></td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </table>
            </div>

            <!--            Order Section-->
            <div id="orderDiv"  style="display:block">

                <form name="order_form" action="OrderHandler" method="POST">

                    <table border="0">
                        <tr>
                            <td>Customer Name </td>
                            <td><input type='text' name='cust_name'><br></td>
                            <td><input type="submit" value="Submit Order"></td>
                        </tr>
                    </table>

                    <table class="ordertable" id="myTable">

                        <tr>
                            <td> Item ID </td>
                            <td> <input type='text' name='item_id0'></td>
                            <td>  Qty </td>
                            <td><input type='text' name='qty0'></td>
                        </tr>
                        <tr>
                            <td><button type="button" onclick="myFunction()">Add Item</button> </td>
                            <td class="empty"></td>
                            <td class="empty"></td>
                            <td class="empty"></td>
                        </tr>

                    </table>

                    <script>
                        var count = 1;
                        function myFunction() {
                            var table = document.getElementById("myTable");
                            var row = table.insertRow(count);
                            var cell4 = row.insertCell(0);
                            var cell3 = row.insertCell(0);
                            var cell2 = row.insertCell(0);
                            var cell1 = row.insertCell(0);
                            cell1.innerHTML = " Item ID ";
                            cell2.innerHTML = "<input type='text' name='item_id" + count + "'>";
                            cell3.innerHTML = "   Qty ";
                            cell4.innerHTML = "<input type='text' name='qty" + count + "'>";
                            count++;
                        }
                    </script>
                </form>

            </div>

            <div id="placedDiv"  style="display:none;">
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
        <!--        <div id="footer">
                    Copyright © 2016 UCSC 2nd Year SD Project Group #12
                </div>-->
    </body>
</html>

