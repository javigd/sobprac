<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="formHandler" class="cat.urv.deim.sob.beans.FormHandler" scope="request" />
<jsp:setProperty name="formHandler" property="*" />

<html>
    <head>
        <title>GoShort! | Sign up</title>
        <link href="design/ion/css/style.css" type="text/css" rel="stylesheet">
    </head>
    <body>
        <br>
        <h2>Sign up</h2>
        <pre>
            <%
                if (request.getAttribute("responseMessage") != null) {
                    out.print(request.getAttribute("responseMessage"));
                }
            %>
        </pre>
        <br>
        <form id="signupform" method="post" action="signupsubmit.do">
            <input type="hidden" name="form_action" value="signup" />
            <table>
                <tr>
                    <td>username:</td>
                    <td> 
                        <input type="text" name="username" />
                    </td>
                </tr>
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
