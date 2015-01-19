<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="urlbean" class="cat.urv.deim.sob.beans.UrlBean" scope="request" />
<jsp:setProperty name="urlbean" property="*" />

<html>
    <head>
        <title>GoShort! | Edit new URL</title>
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
            function validateShortUrl() {
                if (!target)
                    target = document.getElementById("shortUrl");
                var url = "iforgot.do?form_action=shortUrlAjax&shortUrl=" + escape(target.value);
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
                var userMessageElement = document.getElementById("shortUrl");
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
        <%            //allow access only if session exists
            String user = null;
            if (session.getAttribute("user") == null) {
                response.sendRedirect("login.jsp");
            } else {
                user = (String) session.getAttribute("user");
            }
        %>
        <div class="container" >
            <div class="row">
                <div class="col-sm-1 col-lg-12 col-sm-1">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <strong> Your proposed URL! </strong>
                        </div>
                        <div class="panel-body">
                            <form id="urlform" method="post" action="urlconf.do">
                                <input type="hidden" name="form_action" value="urlconf" />
                                <fieldset>
                                    <div class="row">
                                        <div class="col-sm-12 col-md-10 col-md-offset-1">
                                            <div class="alert alert-info text-center" >
                                                <strong>Remember!</strong><br>You can edit your long URL and the shortened one as well!
                                            </div>
                                            <%
                                                if (request.getAttribute("resultMessage") != null) {
                                                    out.print("<div class=\"alert alert-danger text-center\">"
                                                            + "<i class=\"glyphicon glyphicon-exclamation-sign\"></i> "
                                                            + request.getAttribute("resultMessage")
                                                            + "</div>"
                                                    );
                                                }
                                            %>

                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <i class="glyphicon glyphicon-pushpin"></i>
                                                    </span> 
                                                    <input class="form-control" type="text" name="longUrl" value="<%=request.getAttribute("longUrl")%>"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                                                    <span class="input-group-addon"><i class="text-center"><%=request.getAttribute("prefix")%></i></span>
                                                    <span><input id="shortUrl" class="form-control" type="text" name="shortUrl" value="<%=request.getAttribute("shortUrl")%>" onkeyup="validateShortUrl()"></span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <input id="submit_btn" type="submit" class="btn btn-lg btn-primary btn-block" value="Validate">
                                            </div>
                                        </div>
                                    </div>
                                </fieldset>
                            </form>
                            <div class="panel-footer">            
                                Did you make a mistake?!
                                <a href="index.jsp">
                                    <input type="button" class="btn btn-lg btn-info btn-block" value="Return to my URL's!" >
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            validateShortUrl();
        </script>

    </body>
</html>