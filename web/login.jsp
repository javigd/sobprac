<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="formHandler" class="cat.urv.deim.sob.beans.FormHandler" scope="request" />
<jsp:setProperty name="formHandler" property="*" />

<!DOCTYPE html>
<html>
    <head>
        <title>GoShort! | Log in</title>
        <link rel="stylesheet" type="text/css" href="design/css/bootstrap-theme.css"/>
        <link rel="stylesheet" type="text/css" href="design/css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="design/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="design/css/OwnCss.css"/>

    </head>
    <body>
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
                            <div class="panel-footer ">
                                Not GoShort user yet? 
                                <a href="signup.jsp">
                                    <input type="button" class="btn btn-lg btn-primary btn-block" value="Register!" >
                                </a><br>
                                 Did you forget your GoShort password? 
                                 <a href="resetpass.jsp">
                                    <input type="button" class="btn btn-lg btn-info btn-block" value="Remember my password!" >
                            </div>
                            
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    </body>
</html>



