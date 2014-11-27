<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>GoShort! | Remember password</title>
        <%@include file="header.html" %>
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
                                        <div class="alert alert-info text-center" ><strong>Don't worry!</strong><br>An email will be sent to your e-mail to remember your password! </div>
                                    </div>
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
                                                    <input class="form-control" placeholder="Your Email" name="email" type="text" autofocus="">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <input type="submit" class="btn btn-lg btn-primary btn-block" value="Remember my password!">
                                            </div>
                                        </div>

                                        <herror>
                                            <%
                                                if (request.getAttribute("responseMessage") != null) {
                                                    out.print(request.getAttribute("responseMessage"));
                                                }
                                            %>
                                        </herror>
                                    </div>
                                    <div class="panel-footer">
                                        Do you get lost..?
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

<!--
<html>
    <head>
        <title>GoShort! | Reset your password</title>
    </head>
    <body>
        <br>
        <h2>Reset your password</h2>
        <pre>
          
        </pre>
        <br>
        <form id="iforgotform" method="post" action="iforgot.do">
            <input type="hidden" name="form_action" value="iforgot" />
            <table>
                <tr>
                    <td>Your e-mail:</td>
                    <td>
                        <input type="text" name="email"/>
                    </td>
                </tr>
                An e-mail will be sent to you with proper instructions in order to reset your password
                <tr>
                    <td>
                        <input type="submit" name="enter_button" value="Enter" />
                    </td>
                    <td>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
-->
