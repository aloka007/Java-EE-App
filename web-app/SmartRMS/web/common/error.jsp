<%-- 
    Document   : error
    Created on : 28-Aug-2016, 10:25:19
    Author     : Tharinda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
        <link rel="stylesheet" type="text/css" href="resources/css/mystyle.css">
    </head>
    <body>
        <style>
            .error{
                background-color:lightcyan;
                font-family: "Arial",sans-serif;
                font-size: 28px;
                height:600px;
                text-align:center;
                padding:10px 10px;
            }
        </style>
        <div class="error">OOPS! Something Happened!  <br>
            : (<br><br>
            <big>${message}</big><br><br>
            <a href="index.xhtml"><b>Go Back To Login Page</font></b></a>
        </div>
        <div id="footer">
            Smart RMS Error Page
        </div>
    </body>
</html>
