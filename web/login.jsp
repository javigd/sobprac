<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="formHandler" class="cat.urv.deim.sob.beans.FormHandler" scope="request" />
<jsp:setProperty name="formHandler" property="*" />

<!DOCTYPE html>
<html>
    <head>
        <title>GoShort! | Log in</title>
        <%@include file="header.html" %>
    </head>
    <body>

        <%@include file="navbar.html" %> 

        <div class="container" >
            <div class="row">
                <div class="col-sm-6 col-md-4 col-md-offset-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <strong> Welcome to GoShort! </strong>
                        </div>
                        <div class="panel-body">
                            <form id="loginform" method="post" action="loginsubmit.do">
                                <input type="hidden" name="form_action" value="login" />
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
                                                        <i class="glyphicon glyphicon-envelope"></i>
                                                    </span> 
                                                    <input class="form-control" placeholder="Email" name="email" type="text" autofocus="">
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
                                                <input type="submit" class="btn btn-lg btn-primary btn-block" value="Log In">
                                            </div>
                                            </fieldset>
                                            </form>
                                            <div class="panel-footer ">
                                                Not GoShort user yet? 
                                                <a href="signup.jsp">
                                                    <input type="button" class="btn btn-lg btn-primary btn-block" value="Register!" >
                                                </a><br>
                                                Did you forget your GoShort password? 
                                                <a href="iforgot.jsp">
                                                    <input type="button" class="btn btn-lg btn-info btn-block" value="Remember my password!" >
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    </div>
                                    </div>
                                    </div>
                                    </body>
                                    </html>



