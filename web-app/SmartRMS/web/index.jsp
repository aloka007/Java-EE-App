<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html
    xmlns="http://www.w3.org/1999/xhtml"  
    xmlns:h="http://xmlns.jcp.org/jsf/html"
    xmlns:f="http://java.sun.com/jsf/core"  
    xmlns:p="http://primefaces.org/ui">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Smart RMS - Staff Login</title>
        <link rel="stylesheet" type="text/css" href="resources/css/mystyle.css">

    </head>

    <body>
        <script type="text/javascript">
            function ValidateUsename()
            {
                if (document.getElementById("username").value == "")
                {
                    document.getElementById("username").style.borderColor = "red";
                } else
                {
                    document.getElementById("username").style.borderColor = "#cacaca";
                }
            }
            function ValidatePassword()
            {
                if (document.getElementById("password").value == "")
                {
                    document.getElementById("password").style.borderColor = "red";
                } else
                {
                    document.getElementById("password").style.borderColor = "#cacaca";
                    document.getElementById("sub-button").disabled = false;
                }
            }
        </script>



        <div id="header">

            <div class = "banner">
                <b>Smart RMS</b>
                <div class = "desc">Restaurant Management System</div>
            </div>

            <div class = "date">
                <p id="demo"></p>

                <script>
                    var d = new Date();
                    document.getElementById("demo").innerHTML = d.toDateString();
                </script>

            </div>
        </div>

        <div id="nav">
            <b>Login for...</b><br>
            Receptionist<br>
            Cashier<br>
            Chef<br>
            Head Chef<br>
            Manager<br>
        </div>

        <div id="section">


            <div class="please">
                <big><b>Please login to access your workspace</b></big> <br> <br>
            </div>
            <div>



                <!--            <form action="ValidateLogin1" method="POST" name="AdminLogin">-->
                <form action="Login" method="POST" name="AdminLogin">
                    <table align = "center">
                        <tr>
                            <td align = "right">Username: </td> 
                            <td><input type="text" id="username" name="username" onblur="ValidateUsename();" placeholder="Username"><br></td>
                        </tr>

                        <tr>
                            <td align = "right">Password: </td>
                            <td><input type="password" id="password" name="password" onblur="ValidatePassword();" placeholder="Password"><br></td>
                        </tr>

                        <tr>
                            <td align = "center"><!-- <input class = "myButton" type="submit" value="LogIn"> --><br></td>
                        </tr>
                    </table>
                    <input id="sub-button" class = "myButtond" type="submit" value="LogIn" disabled = true>
                </form>

            </div>
<!--            <p:button value="Bookmark" icon="ui-icon-star">
                    <f:param name="productId" value="10" />
                </p:button>-->


            <br>
            <!--<button class = "myButton" type="button" onclick="alert('Hello world!')">Login</button>-->


        </div>

        <div id="footer">
            Copyright © 2016 UCSC 2nd Year SD Project Group #12
        </div>


    </body>
</html>
