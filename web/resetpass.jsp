<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="formHandler" class="cat.urv.deim.sob.beans.FormHandler" scope="request" />
<jsp:setProperty name="formHandler" property="*" />

<!DOCTYPE html>
<html>
    <head>
        <title>GoShort! | Reset password</title>
        <%@include file="header.html" %>
        <%@include file="navBarjsp.jsp"%>
    </head>
    <body>
        <div class="container" >
            <div class="row">
                <div class="col-sm-6 col-md-4 col-md-offset-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <strong> Reset my password! </strong>
                        </div>
                        <div class="panel-body">
                            <form id="resetform" method="post" action="resetpass.do?uid=<%=request.getParameter("uid")%>&ticket=<%=request.getParameter("ticket")%>">
                                <input type="hidden" name="form_action" value="resetpass" />
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
                                                        <i class="glyphicon glyphicon-lock"></i>
                                                    </span> 
                                                    <input class="form-control" placeholder="New password" name="password" type="password" autofocus="">
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
                                                <input type="submit" class="btn btn-lg btn-primary btn-block" value="Reset my password!">
                                            </div>
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
<!DOCTYPE html>
<html>
    <head>
        <title>GoShort! | Reset Password</title>
    </head>
    <body>
        <br>
        <h2>Reset your password</h2>
        <pre>
<%
    if (request.getAttribute("responseMessage") != null) {
        out.print(request.getAttribute("responseMessage"));
    }
%>
</pre>
<br>

<table>
<tr>
    <td>
        Your new password:
    </td>
    <td>
        <input type="password" name="password" />
    </td>
</tr>
<tr>
    <td>
        Repeat password:
    </td>
    <td>
        <input type="password" name="passwordRepeat" />
    </td>
</tr>
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