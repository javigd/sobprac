<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>GoShort! | Reset your password</title>
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
