<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>GoShort!</title>
    </head>
    <body>
        <h2>Login</h2>
        <form id="loginform" method="post" action="/login/loginsubmit.do">
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
                    <td>repeat your password:</td>
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
        <pre>
            <%
                if (request.getAttribute("errorMessage") != null) {
                    out.print(request.getAttribute("errorMessage"));
                }
            %>
        </pre>
        <br>
    </body>
</html>
