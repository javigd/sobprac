<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="formHandler" class="cat.urv.deim.sob.beans.FormHandler" scope="request" />
<jsp:setProperty name="formHandler" property="*" />

<!DOCTYPE html>
<html>
    <head>
        <title>GoShort! | Log in</title>
        <link href="design/ion/css/style.css" type="text/css" rel="stylesheet">
        <link href="design/Form/formcss.css" type="text/css" rel="stylesheet">
    </head>
    <body>
        <pre>
            <%
                request.setAttribute("action", "signupinit");
                if (request.getAttribute("responseMessage") != null) {
                    out.print(request.getAttribute("responseMessage"));
                }
            %>
        </pre>
        <form id="loginform" method="post" action="loginsubmit.do">
            <input type="hidden" name="form_action" value="login" />
            <div class="testbox">
                <h1 > GoShort! Login</h1>
                <hr>
                <input type="text" name="email"  placeholder="Email"/>
                <input type="password" name="password" placeholder="Password"/>
                <a href="iforgot.jsp">Did you forget your password..?</a><br>
                <button class="button" action="submit">Enter</button>
        </form>
        <hr>
    </div>
<h5> Altres opcions</h5>
        <button  href="signup.jsp" class="button">Register </button>
</body>
</html>
