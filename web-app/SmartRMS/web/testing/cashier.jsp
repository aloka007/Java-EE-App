<%-- 
    Document   : chef
    Created on : 16-Aug-2016, 21:35:06
    Author     : Tharinda
--%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Smart RMS - Staff Login</title>
        <link rel="stylesheet" type="text/css" href="mystyle.css">

    </head>

    <body>


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
                <big><b>This is Cashier's Workspace<br>
                        You Are: ${firstname} ${lastname}</b></big> <br> <br>
            </div>






        </div>







        <div id="footer">
            Copyright © 2016 UCSC 2nd Year SD Project Group #12
        </div>


    </body>
</html>

