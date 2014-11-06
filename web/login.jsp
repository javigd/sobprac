<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="formHandler" class="cat.urv.deim.sob.beans.FormHandler" scope="request" />
<jsp:setProperty name="formHandler" property="*" />

<!DOCTYPE html>
<html>
    <head>
        <title>GoShort! | Log in</title>
    </head>
    <body>
        <br>
        <h2>Login</h2>
        <pre>
            <%
                request.setAttribute("action", "signupinit");
                if (request.getAttribute("responseMessage") != null) {
                    out.print(request.getAttribute("responseMessage"));
                }
            %>
        </pre>
        <br>
        <form id="loginform" method="post" action="loginsubmit.do">
            <input type="hidden" name="form_action" value="login" />
            <table>
                <tr>
                    <td>e-mail:</td>
                    <td>
                        <input type="text" name="email" />
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
                    </td>
                </tr>
            </table>
        </form>
        <br>
        <a href="signupinit.do">Register</a>
    </body>
</html>
