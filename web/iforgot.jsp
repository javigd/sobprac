<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>GoShort! | Remember password</title>
        <%@include file="header.html" %>
        <%@include file="navBarjsp.jsp"%>
        <script type="text/javascript">
            var req;
            var target;
            var isIE;
            function initRequest(url) {
                if (window.XMLHttpRequest) {
                    req = new XMLHttpRequest();
                } else if (window.ActiveXObject) {
                    isIE = true;
                    req = new ActiveXObject("Microsoft.XMLHTTP");
                }
            }
            function validateUserEmail() {
                if (!target)
                    target = document.getElementById("emailUser");
                var url = "iforgot.do?form_action=emailUserAjax&emailUser=" + escape(target.value);
                // Invoke initRequest(url) to create XMLHttpRequest object
                initRequest(url);
                // The "processRequest" function is set as a callback function.
                // (Please note that, in JavaScript, functions are first-class objects: they
                // can be passed around as objects.  This is different from the way
                // methods are treated in Java programming language.)
                req.onreadystatechange = processRequest;
                req.open("GET", url, true);
                req.send(null);
            }
            // Callback function that gets invoked asynchronously by the browser
            // when the data has been successfully returned from the server.
            function processRequest() {
                if (req.readyState == 4) {
                    if (req.status == 200) {
                        // Extract "true" or "false" from the returned data from the server.
                        // The req.responseXML should contain either <valid>true</valid> or <valid>false</valid>
                        var message = req.responseXML.getElementsByTagName("valid")[0].childNodes[0].nodeValue;
                        // Call "setMessageUsingDOM(message)" function to display
                        // "Valid User Id" or "Invalid User Id" message.
                        setMessageUsingDOM(message);
                        // If the user entered value is not valid, do not allow the user to
                        // click submit button.
                        var submitBtn = document.getElementById("submit_btn");
                        if (message == "true") {
                            submitBtn.disabled = true;
                        } else {
                            submitBtn.disabled = false;
                        }
                    }
                }
            }
            // Function in which message indicating the validity of the data gets displayed
            function setMessageUsingDOM(message) {
                var userMessageElement = document.getElementById("emailUser");
                if (message == "true") {
                    userMessageElement.style.borderColor = "red";
                } else {
                    userMessageElement.style.borderColor = "green";
                }
            }
            
            function disableSubmitBtn() {
                var submitBtn = document.getElementById("submit_btn");
                submitBtn.disabled = true;
            }
        </script>
    </head>
    <body>
        <div class="container" >
            <div class="row">
                <div class="col-sm-6 col-md-4 col-md-offset-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <strong> Remember my password! </strong>
                        </div>
                        <div class="panel-body">
                            <form id="iforgotform" method="post" action="iforgot.do">
                                <input type="hidden" name="form_action" value="iforgot" />
                                <fieldset>
                                    <div class="row">
                                        <div class="alert alert-info text-center" ><strong>Don't worry!</strong><br>An email will be sent to your e-mail address so you can reset your password! </div>
                                    </div>
                                    <%                                        if (request.getAttribute("responseMessage") != null) {
                                            out.print("<div class=\"row\">"
                                                    + "<div class=\"form-group \">"
                                                    + "<div class=\"center-block\"> "
                                                    + "<div class=\"alert alert-danger text-center\">"
                                                    + "<i class=\"glyphicon glyphicon-exclamation-sign\"></i> "
                                                    + request.getAttribute("responseMessage")
                                                    + "</div>"
                                                    + "</div>"
                                                    + "</div>"
                                                    + "</div>");
                                        }
                                    %>
                                    <div class="row">
                                        <div class="center-block">
                                            <img class="profile-img" src="img/IconLogo.png" alt=" ">
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-12 col-md-10 col-md-offset-1">
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <i class="glyphicon glyphicon-envelope"></i>
                                                    </span> 
                                                    <input id="emailUser" class="form-control" placeholder="Your Email" name="email" type="text" autofocus="" onkeyup="validateUserEmail()">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <input id="submit_btn" type="submit" class="btn btn-lg btn-primary btn-block" value="Remember my password!">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="panel-footer">
                                        Did you get lost?
                                        <a href="login.jsp" style="text-decoration: none">
                                            <input type="button" class="btn btn-lg btn-info btn-block" value="Back to login page" >
                                        </a>
                                    </div>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

