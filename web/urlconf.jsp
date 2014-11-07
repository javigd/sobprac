<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="urlbean" class="cat.urv.deim.sob.beans.UrlBean" scope="request" />
<jsp:setProperty name="urlbean" property="*" />

<html>
    <head>
        <title>GoShort! | New URL</title>
        <link href="design/ion/css/style.css" type="text/css" rel="stylesheet">
    </head>
    <body>
        <%
            //allow access only if session exists
            String user = null;
            if (session.getAttribute("user") == null) {
                response.sendRedirect("login.jsp");
            } else {
                user = (String) session.getAttribute("user");
            }
        %>
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
