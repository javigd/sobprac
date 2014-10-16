<jsp:useBean id="user" class="cat.urv.deim.sob.models.SOBUser" scope="request" />
<html>
    <head>
        <title>User details</title>
    </head>
    <body>
        <h2>User details</h2>  
        <form method="post" action="controller.do">
            <input type="hidden" name="form_action" value="write" />
            <table>
                <tr>
                    <td>
                        First Name:
                    </td>
                    <td>
                        <input type="text" 
                               name="first_name" 
                               value="<jsp:getProperty name="user" property="firstName" />" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Last Name:
                    </td>
                    <td>
                        <input type="text" 
                               name="last_name" 
                               value="<jsp:getProperty name="user" property="lastName" />" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Email:
                    </td>
                    <td>
                        <input type="text" 
                               name="email" 
                               value="<jsp:getProperty name="user" property="email" />" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Phone:
                    </td>
                    <td>
                        <input type="text" 
                               name="phone"
                               value="<jsp:getProperty name="user" property="phone" />" />
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
            <jsp:getProperty name="user" property="message" />
        </pre>
    </body>
</html>
