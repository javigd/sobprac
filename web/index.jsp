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
        <%@ include file="header.jsp"%>
        <div id="page-wrap">
            
            <%
                // allow access only if session exists, redirect to controller if 
                // accessed from external sources in order to load data
                if (session.getAttribute("user") == null) {
                    response.sendRedirect("login.jsp");
                } else if (request.getAttribute("loadedUrls") == null
                        && request.getAttribute("responseMessage") == null) {
                    response.sendRedirect("index.do");
                }
            %>
          
            <br>
            <br>
            <div class="testboxUrl">
                <h3>Your URLs:</h3>
                <hr>
                <table>
                    <tr>
                        <th width="70% ">Long URL</th>
                        <th width="20">goShort! URL</th>
                        <th width="10%">Visits</th>
                    </tr>
                    <div style="padding-bottom: 12px;">
                    <herror >
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
                                out.print("<td><a href=\"rt/" + urlBean.getShortUrl() + "\" target=\"_blank\">" + request.getAttribute("prefix") + urlBean.getShortUrl() + "</a></td>");
                                out.print("<td>" + urlBean.getNvisits() + "</td>");
                                out.print("</tr>");
                            }
                        }
                    %>
                    </herror>
                    </div>
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
                    
                    <a class="button" href="newurl.jsp">Shorten a new URL!</a>
                    
                    </div>
                </form>
            </div>
            <div id="footerfix">
                <%@ include file="footer.html"%>
            </div>
    </body>
</html>
