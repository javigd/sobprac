<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GoShort! | Bad URL</title>
    </head>
    <body>
        <br>
        <h2>goShort!</h2>
        <h3><%=request.getAttribute("resultMessage")%></h3>
        <br>
        <h1><%=request.getAttribute("prefix")%><%=request.getAttribute("shortUrl")%></h1>
    </body>
</html>
