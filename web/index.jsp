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
        <link href="design/Form/formcss.css" type="text/css" rel="stylesheet">
    </head>
    <body>
        <%@ include file="header.html"%>

        <%
            // allow access only if session exists, redirect to controller if 
            // accessed from external sources in order to load data
            if (session.getAttribute("user") == null) {
                response.sendRedirect("login.jsp");
            } else if (request.getAttribute("loadedUrls") == null
                    && request.getAttribute("responseMessage") == null) {
                response.sendRedirect("index.do");
            }
            String userName = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("user")) {
                        userName = cookie.getValue();
                    }
                }
            }
        %>

        Welcome back, <%=userName%>
        <br />
        <br />
        <div class="testboxUrl">
            <h3>Your URLs:</h3>
            <hr>
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
                }
            %>
        </table>
        <span>
            <%
                if (request.getAttribute("npages") != null && request.getAttribute("currentPage") != null) {
                    Integer npages = (Integer) request.getAttribute("npages");
                    Integer currentpg = (Integer) request.getAttribute("currentPage");
                    Integer showpg = (Integer) request.getAttribute("showPages");
                    if (currentpg >= showpg) out.print("... ");
                    if (currentpg > 1) out.print("<a href=index.do?page=" + (currentpg - 1) + "> < </a> ");
                    int inipg = (currentpg < showpg ? 1 : currentpg - showpg + 2);
                    for (int i = inipg; i < inipg + showpg; i++) {
                        if (i > npages) break;
                        if (i == currentpg) {
                            out.print(i + " ");
                        } else {
                            out.print("<a href=index.do?page=" + i + ">" + i + "</a> ");
                        }
                    }
                    if (currentpg != npages) {
                        out.print("<a href=index.do?page=" + (currentpg + 1) + "> > </a> ");
                        if (npages > showpg && currentpg < showpg) out.print("...");
                    }
                }%>
        </span>
        <form id="logoutServlet" method="post" action="logout.do">
            <input type="hidden" name="form_action" value="logout" />
            <input type="submit" value="Logout" >
        </form>
    </body>
</html>
