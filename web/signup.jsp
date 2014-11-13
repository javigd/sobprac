<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="formHandler" class="cat.urv.deim.sob.beans.FormHandler" scope="request" />
<jsp:setProperty name="formHandler" property="*" />

<!DOCTYPE html>
<html>
    <head>
        <title>GoShort! | Sign up</title>
        <link href="design/ion/css/style.css" type="text/css" rel="stylesheet">
        <link href="design/Form/formcss.css" type="text/css" rel="stylesheet">
    </head>
    <body>
        <%@ include file="header.html"%>

        <form id="signupform" method="post" action="signupsubmit.do">
            <input type="hidden" name="form_action" value="signup" />
            <div class="testbox">
                <h2>Sign up</h2>
                <hr>
                <input type="text" name="username" placeholder="Username" />
                <input type="text" name="email" placeholder="E-mail"/>
                <input type="password" name="password" placeholder="Password"/>
                <input type="password" name="passwordRepeat" placeholder="Repeat the password"/>
                <input type="submit" name="enter_button" value="Enter" />
        </form>
        <br><br>
    <herror>
        <%
            if (request.getAttribute("responseMessage") != null) {
                out.print(request.getAttribute("responseMessage"));
            }
        %>
        <br>
    </herror>
    <a href='login.jsp'> Back to Login page </a>
</div>
</body>
</html>
