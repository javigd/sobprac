<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    </body>
</html>
