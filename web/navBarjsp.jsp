<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>NavBar</title>
        <%@include file="header.html"%>
    </head>
    <body>
        <nav class="navbar navbar-default navbar-fixed-top nav" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <%
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
                    <a class="navbar-brand " href="index.do"> <span><img alt="GoShort!" src="img/IconLogoNavBar.png"></span>
                        GoShort!
                    </a> 
                </div>
                <%
                    if (userName != null) {
                        out.print("<p class=\"navbar-text \">"
                                + "Welcome, "
                                + "<a href=\"index.do\" class=\"navbar-link\">"
                                + "<strong>" + userName +"</strong>"
                                + "</a></p>");
                    }
                %>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="https://github.com/javigd/sobprac" target="_blank"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> What's GoShort!?     </a></li>
                </ul>
            </div>
        </nav>
    </body>
</html>
