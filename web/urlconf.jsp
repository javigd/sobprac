<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="urlbean" class="cat.urv.deim.sob.beans.UrlBean" scope="request" />
<jsp:setProperty name="urlbean" property="*" />

<html>
    <head>
        <title>GoShort! | Edit new URL</title>
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
                            <strong> Your proposed URL! </strong>
                        </div>
                        <div class="panel-body">
                            <form id="urlform" method="post" action="urlconf.do">
                                <input type="hidden" name="form_action" value="urlconf" />
                                <fieldset>
                                    <div class="row">
                                        <div class="col-sm-12 col-md-10 col-md-offset-1">
                                            <div class="alert alert-info text-center" >
                                                <strong>Remember!</strong><br>You can edit your long URL and the shortened one as well!
                                            </div>
                                            <%
                                                if (request.getAttribute("resultMessage") != null) {
                                                    out.print("<hr>"
                                                            + "<div class=\"form-group\">"
                                                            + "<div class=\"input-group\">"
                                                            + "<span class=\"input-group text-center\">"
                                                            + "<div class=\"alert alert-danger text-center\">"
                                                            + "<i class=\"glyphicon glyphicon-exclamation-sign\"></i>"
                                                            + "</span> " + request.getAttribute("resultMessage")
                                                            + "</div>"
                                                            + "</div>"
                                                            + "</div>"
                                                            + "<hr>");
                                                }
                                            %>
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <i class="glyphicon glyphicon-pushpin"></i>
                                                    </span> 
                                                    <input class="form-control" type="text" name="longUrl" value="<%=request.getAttribute("longUrl")%>"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                                                    <span class="input-group-addon"><i class="text-center"><%=request.getAttribute("prefix")%></i></span>
                                                    <span><input class="form-control" type="text" name="shortUrl" value="<%=request.getAttribute("shortUrl")%>"></span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <input type="submit" class="btn btn-lg btn-primary btn-block" value="Validate">
                                            </div>
                                        </div>
                                    </div>
                                </fieldset>
                            </form>
                            <div class="panel-footer">            
                                Did you make a mistake?!
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