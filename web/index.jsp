<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="cat.urv.deim.sob.beans.UrlBean"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
    <head>
        <title>
            GoShort!
        </title>
    </head>
    <body>
        <%
            //allow access only if session exists
            String user = null;
            if (session.getAttribute("user") == null) {
                response.sendRedirect("logininit.do");
            } else {
                user = (String) session.getAttribute("user");
            }
            request.setAttribute("action", "newurlinit");
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
        <a href="newurlinit.do">Shorten a new URL!</a>
        <br />
        <table>
            <%
                if (request.getAttribute("responseMessage") != null) {
                    out.print(request.getAttribute("responseMessage"));
                }
            %>
            <%
                if (request.getAttribute("urls") != null) {
                    List<UrlBean> myurls = (List<UrlBean>) request.getAttribute("urls");
                    for (UrlBean urlBean : myurls) {
                        out.print("<tr>");
                        out.print("<td>" + urlBean.getLongUrl() + "</td>");
                        out.print("<td>" + urlBean.getShortUrl() + "</td>");
                        out.print("<td>" + urlBean.getNvisits() + "</td>");
                        out.print("</tr>");
                    }
                }
            %>
        </table>
    </body>
</html>
