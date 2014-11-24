<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="formHandler" class="cat.urv.deim.sob.beans.FormHandler" scope="request" />
<jsp:setProperty name="formHandler" property="*" />

<!DOCTYPE html>
<html>
    <head>
        <title>GoShort! | Sign Up</title>
        <%@include file="header.html" %>
    </head>
    <body>
        <div class="container" style="margin-top: 40px">
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
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <i class="glyphicon glyphicon-lock"></i>
                                                    </span>
                                                    <input class="form-control" placeholder="Repeat password" name="passwordRepeat" type="password" autofocus="">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <input type="submit" class="btn btn-lg btn-primary btn-block" value="Register!">
                                            </div>
                                        </div>
                                        <herror>
                                            <%
                                                if (request.getAttribute("responseMessage") != null) {
                                                    out.print(request.getAttribute("responseMessage"));
                                                }
                                            %>
                                        </herror>
                                </fieldset>
                            </form>
                            <a href="login.jsp" style="text-decoration: none">
                                <input type="submit" class="btn btn-lg btn-primary btn-block" value="Back to login page" >
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>


