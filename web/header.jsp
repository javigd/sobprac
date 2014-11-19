
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Header</title>
        <link href="design/ion/css/style.css" type="text/css" rel="stylesheet">
        <link href="design/Form/formcss.css" type="text/css" rel="stylesheet">
        <link rel="shortcut icon" href="img/favicon.png" />

    </head>
    <body>
        <div id="header">
            <table>
                <tfoot>
                <td width="70%">
                    <div><a href="index.do"><img id="headerimage" src="img/HeaderLogo.png" alt=""></a></div>
                </td>
                <td width="20%">
                    <%
                        String userName = null;
                        Cookie[] cookies = request.getCookies();
                        if (cookies != null) {
                            for (Cookie cookie : cookies) {
                                if (cookie.getName().equals("user")) {
                                    userName = cookie.getValue();
                                }
                            }
                        }%>
                    <div style="text-align: right">
                        <hUserHeader>
                            <% if (session.getAttribute("user") != null) {
                            out.print("Welcome back, " + userName);%>
                        </hUserHeader>
                    </div>
                <td widht="20%" style="text-align: left">
                    <form id="logoutServlet" method="post" action="logout.do">
                        <input type="hidden" name="form_action" value="logout" />
                        <input type="submit" value="Logout" >
                    </form>
                </td>
                <% }
                %>


                </td>
                </tfoot>
            </table>


        </div>
    </body>
</html>













