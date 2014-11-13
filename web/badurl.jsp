<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GoShort! | Bad URL</title>
        <link href="design/ion/css/style.css" type="text/css" rel="stylesheet">
    </head>
    <body>
        <%@ include file="header.html"%>

        <br>
        <h2>goShort!</h2>
        <h3><%=request.getAttribute("resultMessage")%></h3>
        <br>
        <h1><%=request.getAttribute("prefix")%><%=request.getAttribute("shortUrl")%></h1>
    </body>
</html>
