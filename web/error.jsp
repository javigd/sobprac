<%@ page language="java" isErrorPage="true" import="java.io.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>GoShort! | Error Page!</title>
        <%@include file="header.html" %>
       
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
                                        <div class="alert alert-danger text-center" ><span class="glyphicon glyphicon-exclamation-sign"> Error Code: ${pageContext.errorData.statusCode}</span></div>
                                        
                                        Request URI:
                                        <br>
                                        <input class="form-control" value="${pageContext.request.scheme}://${header.host}${pageContext.errorData.requestURI}" type="text" readonly>
                                        <br>
                                        <a href="index.jsp">
                                            <input type="button" class="btn  btn-lg btn-primary btn-block" value="Get me back to GoShort!">
                                        </a>

                                        <% if (response.getStatus() == 500) {%>
                                        Cause:<div class="alert alert-danger text-center" ><span class="glyphicon glyphicon-exclamation-sign"><%=exception.getMessage()%></span></div>

                                        Cause:<%
                                            StringWriter stringWriter = new StringWriter();
                                            PrintWriter printWriter = new PrintWriter(stringWriter);
                                            exception.printStackTrace(printWriter);
                                            out.println(stringWriter);
                                            printWriter.close();
                                            stringWriter.close();
                                            }%>
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
