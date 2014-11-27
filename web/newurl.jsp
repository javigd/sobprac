<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="urlbean" class="cat.urv.deim.sob.beans.UrlBean" scope="request" />
<jsp:setProperty name="urlbean" property="*" />

<html>
    <head>
        <title>GoShort! | New Url</title>
        <%@include file="header.html" %>
    </head>
    <body>
        <%@include file="navbar.html" %> 

        <%
            //allow access only if session exists
            String user = null;
            if (session.getAttribute("user") == null) {
                response.sendRedirect("login.jsp");
            } else {
                user = (String) session.getAttribute("user");
            }
        %>

        <div class="container" >
            <div class="row">
                <div class="col-sm-1 col-lg-12 col-sm-1">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <strong> Add a new URL and make it GoShort! </strong>
                        </div>
                        <div class="panel-body">
                            <form id="urlform" method="post" action="urlsubmit.do">
                                <input type="hidden" name="form_action" value="newurl" />
                                <fieldset>
                                    <%
                                        if (request.getAttribute("resultMessage") != null) {
                                            out.print("<hr>"
                                                    + "<div class=\"form-group \">"
                                                    + "<div class=\"input-group\">"
                                                    + "<span class=\"input-group\">"
                                                    + "<div class=\"alert alert-danger\">"
                                                    + "<i class=\"glyphicon glyphicon-exclamation-sign\"></i>"
                                                    + "</span> " + request.getAttribute("resultMessage")
                                                    + "</div>"
                                                    + "</div>"
                                                    + "</div>"
                                                    + "<hr>"  );
                                        }
                                    %>         
                                    <div class="row">
                                        <div class="col-sm-12 col-md-10 col-md-offset-1">
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <i class="glyphicon glyphicon-link"></i>
                                                    </span> 
                                                    <input class="form-control" placeholder="Your long URL" name="longUrl" type="text" autofocus="">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <input type="submit" class="btn btn-lg btn-primary btn-block" value="GoShort - it!">
                                            </div>
                                            <div class="text-center">
                                            </div>
                                        </div>
                                </fieldset>
                            </form>
                            <div class="panel-footer">
                                I will add a new one later!
                                <a href="index.jsp">
                                    <input type="button" class="btn btn-lg btn-info btn-block" value="Return to my URL's!" >
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>




<!--<html>
    <head>
        <title>GoShort! | New URL</title>
    </head>
    <body>

<br>
<h2>Make your URL goShort!</h2>
<pre>

</pre>
<br>
<form id="urlform" method="post" action="urlsubmit.do">
<input type="hidden" name="form_action" value="newurl" />
<table>
    <tr>
        <td>Your Long URL:</td>
        <td> 
            <input type="text" name="longUrl" />
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
<a href="index.do">Cancel</a>
</body>
</html>
-->