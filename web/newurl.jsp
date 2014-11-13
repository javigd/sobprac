<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="urlbean" class="cat.urv.deim.sob.beans.UrlBean" scope="request" />
<jsp:setProperty name="urlbean" property="*" />

<html>
    <head>
        <title>GoShort! | New URL</title>
        <link href="design/ion/css/style.css" type="text/css" rel="stylesheet">
        <link href="design/Form/formcss.css" type="text/css" rel="stylesheet">

    </head>
    <body>
        <%@ include file="header.html"%>

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
        <div class="testboxUrl">
           

        <form id="urlform" method="post" action="urlsubmit.do">
            <input type="hidden" name="form_action" value="newurl" />
                        <input class="longUrlText" type="text" name="longUrl" placeholder="Introduce your long URL"/>
                        <input type="submit" name="enter_button" value="Enter" />
                        <a href="index.do" class="button">Cancel</a>
                        <br><br><br>
        </form>
        <br><br>
         <herror>
            <%
                if (request.getAttribute("resultMessage") != null) {
                    out.print(request.getAttribute("resultMessage"));
                }
            %>
                    </herror>
        </div>
    </body>
</html>
