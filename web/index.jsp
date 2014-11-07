<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="cat.urv.deim.sob.beans.UrlBean"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
    <head>
        <title>
            GoShort!
        </title>
        <link href="design/ion/css/style.css" type="text/css" rel="stylesheet">
    </head>
    <body>
        <%
            //allow access only if session exists
            String user = null;
            if (session.getAttribute("user") == null) {
                response.sendRedirect("login.jsp");
            } else {
                user = (String) session.getAttribute("user");
            }
            String userName = null;
            String sessionID = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("user")) {
                        userName = cookie.getValue();
                    }
                    if (cookie.getName().equals("JSESSIONID")) {
                        sessionID = cookie.getValue();
                    }
                }
            }
        %>

        Welcome back, <%=userName%>
        <br />
        <h2>GoShort! The handy way to shorten your URLs</h2>
        <br />
        <h3>Your URLs:</h3>
        <a href="newurl.jsp">Shorten a new URL!</a>
        <br />
        <table>
            <tr>
                <th>Long URL</th>
                <th>goShort! URL</th>
                <th>Visits</th>
            </tr>
            <%
                if (request.getAttribute("responseMessage") != null) {
                    out.print(request.getAttribute("responseMessage"));
                }
            %>
            <%
                if (request.getAttribute("loadedUrls") != null) {
                    List<UrlBean> myurls = (List<UrlBean>) request.getAttribute("loadedUrls");
                    for (UrlBean urlBean : myurls) {
                        out.print("<tr>");
                        out.print("<td>" + urlBean.getLongUrl() + "</td>");
                        out.print("<td>" + request.getAttribute("prefix") + urlBean.getShortUrl() + "</td>");
                        out.print("<td>" + urlBean.getNvisits() + "</td>");
                        out.print("</tr>");
                    }
                }
            %>
        </table>
        <form id="logoutServlet" method="post" action="logout.do">
            <input type="hidden" name="form_action" value="logout" />
            <input type="submit" value="Logout" >
        </form>
    </body>
</html>
