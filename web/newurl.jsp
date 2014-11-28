<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="urlbean" class="cat.urv.deim.sob.beans.UrlBean" scope="request" />
<jsp:setProperty name="urlbean" property="*" />

<html>
    <head>
        <title>GoShort! | New Url</title>
        <%@include file="header.html" %>
        <%@include file="navBarjsp.jsp"%>
    </head>
    <body>
        <%            //allow access only if session exists
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
                                    <div class="row">
                                        <div class="col-sm-12 col-md-10 col-md-offset-1">
                                            <%
                                                if (request.getAttribute("resultMessage") != null) {
                                                    out.print("<div class=\"alert alert-danger text-center\">"
                                                            + "<i class=\"glyphicon glyphicon-exclamation-sign\"></i> "
                                                            + request.getAttribute("resultMessage")
                                                            + "</div>"
                                                    );
                                                }
                                            %> 
                                        </div>
                                    </div>
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