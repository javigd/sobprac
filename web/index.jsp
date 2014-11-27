<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="cat.urv.deim.sob.beans.UrlBean"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>

<html>
    <head>
        <title>GoShort!</title>
        <%@include file="header.html" %>
        <%@include file="navbar.html" %>
    </head>
    <body>
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
        <div class="container" >
            <div class="row">
                <div class="col-sm-1 col-lg-12 col-sm-1">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <strong> Welcome to GoShort! </strong>
                        </div>
                        <div class="panel-body">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-sm-12 col-md-10 col-md-offset-1">
                                        <div class="alert alert-info text-left" >Here you have all your <strong>GoShort!</strong> Url's</div>
                                        <!-- Table -->
                                        <table class="table table-striped  ">
                                            <th class="col-sm-offset-2"> # </th>
                                            <th class="col-lg-9" >Long Url</th>
                                            <th class="col-md-6">GoShort! Url</th>
                                            <th class="col-sm-2">Visits</th>
                                                <%
                                                    if (request.getAttribute("loadedUrls") != null) {
                                                        List<UrlBean> myurls = (List<UrlBean>) request.getAttribute("loadedUrls");
                                                        int num = 1;
                                                        for (UrlBean urlBean : myurls) {
                                                            String LongUrl = urlBean.getLongUrl();
                                                            out.print("<tr>");
                                                            out.print("<td>" + num + " </td>");
                                                            out.print("<td><input class=\"form-control\" value=\"" + LongUrl + "\" type=\"text\" readonly></td>");
                                                            out.print("<td><a href=\"rt/" + urlBean.getShortUrl() + "\" target=\"_blank\">" + request.getAttribute("prefix") + urlBean.getShortUrl() + "</a></td>");
                                                            out.print("<td>" + urlBean.getNvisits() + "</td>");
                                                            out.print("</tr>");
                                                            LongUrl = null;
                                                            num++;
                                                        }
                                                    }
                                                %>
                                        </table>
                                        <%
                                            if (request.getAttribute("responseMessage") != null) {
                                                out.print("<div class=\"form-group\">"
                                                        + "<div class=\"input-group\">"
                                                        + "<span class=\"input-group\">"
                                                        + "<div class=\"alert alert-success  text-left\">"
                                                        + "<i class=\"glyphicon glyphicon-exclamation-sign\"></i>"
                                                        + "</span> " + request.getAttribute("responseMessage")
                                                        + "</div>"
                                                        + "</div>"
                                                        + "</div>");
                                            }
                                        %>
                                        <a href="newurl.jsp">
                                            <input type="button" class="btn  btn-lg btn-primary btn-block" value="Shorten a new URL!">
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <%
                                if (request.getAttribute("npages") != null && request.getAttribute("currentPage") != null) {
                                    Integer npages = (Integer) request.getAttribute("npages");
                                    Integer currentpg = (Integer) request.getAttribute("currentPage");
                                    Integer showpg = (Integer) request.getAttribute("showPages");
                                    int i;
                                    out.print("<nav class=\"text-center\">"
                                            + "<ul class=\"pagination\">");
                                    for (i = 1; i <= npages; i++) {
                                        out.print("<li><a href=\"index.do?page=" + i + "\">" + i + "</a></li>");
                                    }
                                    out.print("</ul>"
                                            + "</nav>");
                                }%>
                            <div class="panel-footer">
                                <form id="logoutServlet" method="post" action="logout.do">
                                    I finished my work here!
                                    <input type="hidden" name="form_action" value="logout" />
                                    <input  class="btn btn-lg btn-info btn-block" type="submit" value="Logout" >
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
<!--
//   /*Welcome back, <%//=userName%>*/
<br />
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
<%/*
        // if (request.getAttribute ( 
        //   "responseMessage") != null) {
        // out.print(request.getAttribute("responseMessage"));
    }
%>

</table>
<span>
<%/*
     if (request.getAttribute ( 
     "npages") != null && request.getAttribute("currentPage") != null) {
     Integer npages = (Integer) request.getAttribute("npages");
     Integer currentpg = (Integer) request.getAttribute("currentPage");
     Integer showpg = (Integer) request.getAttribute("showPages");
     if (currentpg >= showpg) {
     out.print("... ");
     }
     if (currentpg > 1) {
     out.print("<a href=index.do?page=" + (currentpg - 1) + "> < </a> ");
     }
     int inipg = (currentpg < showpg ? 1 : currentpg - showpg + 2);
     for (int i = inipg; i < inipg + showpg; i++) {
     if (i > npages) {
     break;
     }
     if (i == currentpg) {
     out.print(i + " ");
     } else {
     out.print("<a href=index.do?page=" + i + ">" + i + "</a> ");
     }
     }
     if (currentpg != npages) {
     out.print("<a href=index.do?page=" + (currentpg + 1) + "> > </a> ");
     if (npages > showpg && currentpg < showpg) {
     out.print("...");
     }
     }
     }*/%>
</span>
<form id="logoutServlet" method="post" action="logout.do">
<input type="hidden" name="form_action" value="logout" />
<input type="submit" value="Logout" >
</form>
</body>
</html>
-->