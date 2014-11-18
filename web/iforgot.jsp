<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>GoShort! | Reset your password</title>
        <link href="design/ion/css/style.css" type="text/css" rel="stylesheet">
        <link href="design/Form/formcss.css" type="text/css" rel="stylesheet">

    </head>
    <body>
        <%@ include file="header.jsp"%>
        <div id="page-wrap">
            <form id="iforgotform" method="post" action="iforgot.do">
                <input type="hidden" name="form_action" value="iforgot" />
                <div class="testbox">
                    <h2>Reset your password</h2>
                    <hr>
                    <input type="text" name="email" placeholder="Insert your email"/>
                    <input type="submit" name="enter_button" value="Enter" />
                    <br><br>
                    <herror><%
                        if (request.getAttribute("responseMessage") != null) {
                            out.print(request.getAttribute("responseMessage"));
                        }
                        %> 
                    </herror>
                    <br><br>
                    <a href="login.jsp">Go back to Login Page..</a>
                </div>
        </div>
        <div id="footerfix">
            <%@ include file="footer.html"%>
        </div>
    </body>
</html>
