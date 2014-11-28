<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>GoShort! | Bad Url!</title>
        <%@include file="header.html" %>
       
    </head>
    <body>
        <div class="container" >
            <div class="row">
                <div class="col-sm-1 col-lg-12 col-sm-1">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <strong> Sorry! There has been an unexpected error! </strong>
                        </div>
                        <div class="panel-body">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-sm-12 col-md-10 col-md-offset-1">
                                        <div class="alert alert-danger text-center" ><span class="glyphicon glyphicon-exclamation-sign"> <%=request.getAttribute("resultMessage")%></span></div>
                                        Requested URL:
                                        <br>
                                        <input class="form-control" value="<%=request.getAttribute("prefix")%><%=request.getAttribute("shortUrl")%>" type="text" readonly>
                                        <br>
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