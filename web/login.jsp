<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="formHandler" class="cat.urv.deim.sob.beans.FormHandler" scope="request" />
<jsp:setProperty name="formHandler" property="*" />

<!DOCTYPE html>
<html>
    <head>
        <title>GoShort! | Log in</title>
        <link rel="icon" href="img/favicon.png"/>
        <link href="design/ion/css/style.css" type="text/css" rel="stylesheet">
        <link href="design/Form/formcss.css" type="text/css" rel="stylesheet">
    </head>
    <body>
        <%@ include file="header.jsp"%>
        <div id="page-wrap">
            <form id="loginform" method="post" action="loginsubmit.do">
                <input type="hidden" name="form_action" value="login" />
                <div class="testbox">
                    <h2>LOGIN</h2>
                    <hr>
                    <input type="text" name="email"  placeholder="Email"/>
                    <input type="password" name="password" placeholder="Password"/>
                    <a href="iforgot.jsp">Did you forget your password..?</a><br>
                    <button class="button" action="submit" margin-left="40px%">Enter</button>
            </form>
            <br>
            <herror>
                <%
                    request.setAttribute("action", "signupinit");
                    if (request.getAttribute("responseMessage") != null) {
                        out.print(request.getAttribute("responseMessage"));
                    }
                %>
            </herror>
            <hr>
            <h5> Other options</h5>
            <a  href="signup.jsp" class="button">Register</a>
        </div>
    </div>
    <div id="footerfix">
        <%@ include file="footer.html"%>
    </div>
</body>
</html>
