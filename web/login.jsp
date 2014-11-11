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
        <br>
     
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
                
            <table>
                <h1> LOGIN </h1>
                <tr>
                    <td>
                        e-mail:<br>
                    <input type="text" name="email" /><br>
                                        
                    </td>
                </tr>
                <tr>
                    <td>
                        password:
                    </td>
                    <td>
                        <input type="password" name="password" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" name="enter_button" value="Enter" />
                    </td>
                    <td>
                        <a href="signup.jsp">Register</a>
                    </td>
                    <td>
                        <a href="iforgot.jsp">I forgot my password</a>
                    </td>
                </tr>
            </table>
        </form>
      </div>
        
    </body>
</html>
