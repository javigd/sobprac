<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="urlbean" class="cat.urv.deim.sob.beans.UrlBean" scope="request" />
<jsp:setProperty name="urlbean" property="*" />

<html>
    <head>
        <title>GoShort! | Edit new URL</title>
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
                                            <hr>
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
                                        <herror>
                                            <%
                                                if (request.getAttribute("responseMessage") != null) {
                                                    out.print(request.getAttribute("responseMessage"));
                                                }
                                            %>
                                        </herror>
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


<!--

<%=request.getAttribute("shortUrl")%>


<html>
    <head>
        <title>GoShort! | New URL</title>
    </head>
    <body>

<br>
<h2>Make your URL goShort!</h2>
<pre>
<%
    if (request.getAttribute("resultMessage") != null) {
        out.print(request.getAttribute("resultMessage"));
    }
%>
</pre>
<br>
<form id="urlform" method="post" action="urlconf.do">
<input type="hidden" name="form_action" value="urlconf" />
<table>
    <tr>
        <td>Your Long URL:</td>
        <td> 
            <input type="text" name="longUrl" value="<%=request.getAttribute("longUrl")%>" />
        </td>
    </tr>
    <tr>
        <td>Your proposed short URL:</td>
        <td>
<%=request.getAttribute("prefix")%><input type="text" name="shortUrl" value="<%=request.getAttribute("shortUrl")%>" />
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