<%@page import="cat.urv.deim.sob.exceptions.SOBException"%>
<jsp:useBean id="formHandler" class="cat.urv.deim.sob.handlers.FormHandler" scope="request" />
<jsp:setProperty name="formHandler" property="*" />

<html>
    <head>
        <title>GoShort!</title>
    </head>
    <body>
        <h2>Sign up</h2>
        <form method="post" action="controller.do">
            <input type="hidden" name="form_action" value="signup" />
            <table>
                <tr>
                    <td>
                        username:
                    </td>
                    <td>
                        <input type="text" 
                               name="username" />
                    </td>
                </tr>
                <tr>
                    <td>
                        e-mail:
                    </td>
                    <td>
                        <input type="text" 
                               name="email" />
                    </td>
                </tr>
                <tr>
                    <td>
                        password:
                    </td>
                    <td>
                        <input type="password" 
                               name="password" />
                    </td>
                </tr>
                <tr>
                    <td>
                        repeat your password:
                    </td>
                    <td>
                        <input type="password" 
                               name="passwordRepeat" />
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
        <%
//            if (request.getAttribute("errorMessage") != null) {
//                out.print(request.getAttribute("errorMessage"));
//            }
//            
//            try {
//                formHandler.validate();
//            } catch (SOBException e) {
//                request.setAttribute("error", e.getError());
//            }
        %>
        <br>
    </body>
</html>
