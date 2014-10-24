<jsp:useBean id="user" class="cat.urv.deim.sob.models.SOBUser" scope="request" />
<html>
    <head>
        <title>GoShort!</title>
    </head>
    <body>
        <h2>Sign in</h2>  
        <form method="post" action="controller.do">
            <input type="hidden" name="form_action" value="signin" />
            <table>
                <tr>
                    <td>
                        username
                    </td>
                    <td>
                        <input type="text" 
                               name="username" 
                               value="<jsp:getProperty name="user" property="username" />" />
                    </td>
                </tr>
                <tr>
                    <td>
                        e-mail
                    </td>
                    <td>
                        <input type="text" 
                               name="email" 
                               value="<jsp:getProperty name="user" property="email" />" />
                    </td>
                </tr>
                <tr>
                    <td>
                        password
                    </td>
                    <td>
                        <input type="password" 
                               name="password"
                               value="<jsp:getProperty name="user" property="password" />" />
                    </td>
                </tr>
                <tr>
                    <td>
                        repeat your password
                    </td>
                    <td>
                        <input type="password" 
                               name="password"
                               value="<jsp:getProperty name="user" property="password" />" />
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
        <%if (request.getParameter("error") != null) {%>
        <%= request.getParameter("error") %>
        <%}%>
        <br>
        <pre>
            <jsp:getProperty name="user" property="message" />
        </pre>
    </body>
</html>
