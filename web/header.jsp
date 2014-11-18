<%-- 
    Document   : header
    Created on : 18-nov-2014, 10:44:03
    Author     : Max
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Header</title>
        <link href="design/ion/css/style.css" type="text/css" rel="stylesheet">
        <link href="design/Form/formcss.css" type="text/css" rel="stylesheet">

    </head>
    <body>
        <div id="header">
            <table>
                <tfoot>
                <td>
                    <div><a href="index.do"><img id="headerimage" src="img/HeaderLogo.png" alt=""></a></div>
                </td>
                <td>
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
                        <% if (userName != null){
                            out.print("Welcome back, " + userName);
                            }
                        %>
                </td>
                </tfoot>
            </table>


        </div>
    </body>
</html>













