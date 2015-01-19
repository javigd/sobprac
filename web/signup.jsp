<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="formHandler" class="cat.urv.deim.sob.beans.FormHandler" scope="request" />
<jsp:setProperty name="formHandler" property="*" />

<!DOCTYPE html>
<html>
    <head>
        <title>GoShort! | Sign Up</title>
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
                var url = "signup.do?form_action=emailUserAjax&emailUser=" + escape(target.value);
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
                        if (message == "false") {
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
                if (message == "false") {
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
        <div class="container">
            <div class="row">
                <div class="col-sm-6 col-md-4 col-md-offset-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <strong> You are really close to be part of GoShort! </strong>
                        </div>
                        <div class="panel-body">
                            <form id="signupform" method="post" action="signupsubmit.do">
                                <input type="hidden" name="form_action" value="signup" />
                                <fieldset>
                                    <%
                                        if (request.getAttribute("responseMessage") != null) {
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
                                                        <i class="glyphicon glyphicon-user"></i>
                                                    </span> 
                                                    <input class="form-control" placeholder="Username" name="username" type="text" autofocus="">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <i class="glyphicon glyphicon-envelope"></i>
                                                    </span> 
                                                    <div id="userIdMessage"></div>
                                                    <input id="emailUser" class="form-control" placeholder="Email" name="email" type="text" autofocus="" onkeyup="validateUserEmail()">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <i class="glyphicon glyphicon-lock"></i>
                                                    </span>
                                                    <input class="form-control" placeholder="Password" name="password" type="password" autofocus="">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <i class="glyphicon glyphicon-lock"></i>
                                                    </span>
                                                    <input class="form-control" placeholder="Repeat password" name="passwordRepeat" type="password" autofocus="">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <input id="submit_btn" type="submit" class="btn btn-lg btn-primary btn-block" value="Register!">
                                            </div>
                                        </div>
                                </fieldset>
                            </form>
                            <div class="panel-footer">
                                Are you already a member?   
                                <a href="login.jsp">
                                    <input type="submit" class="btn btn-lg btn-info btn-block" value="Back to login page" >
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>


