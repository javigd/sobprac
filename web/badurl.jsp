<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>GoShort! | Bad Url!</title>
        <%@include file="header.html" %>
        <%@include file="navBarjsp.jsp"%>
    </head>
    <body>
        <div class="container" >
            <div class="row">
                <div class="col-sm-1 col-lg-12 col-sm-1">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <strong> Uuups! We have an error here! </strong>
                        </div>
                        <div class="panel-body">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-sm-12 col-md-10 col-md-offset-1">
                                        <div class="alert alert-danger text-center" ><span class="glyphicon glyphicon-exclamation-sign"> <%=request.getAttribute("resultMessage")%></span></div>
                                        We found the problem in the next URL:
                                        <br>
                                        <input class="form-control" value="<%=request.getAttribute("prefix")%><%=request.getAttribute("shortUrl")%>" type="text" readonly>
                                        <br>
                                        <a href="index.jsp">
                                            <input type="button" class="btn  btn-lg btn-primary btn-block" value="Get me back to GoShort!">
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>