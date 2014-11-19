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
        <%@ include file="header.jsp"%>
        <div id="page-wrap">
            <%                //allow access only if session exists
                String user = null;
                if (session.getAttribute("user") == null) {
                    response.sendRedirect("login.jsp");
                } else {
                    user = (String) session.getAttribute("user");
                }
            %>
            <br>
            <div class="testboxUrl">

                <div style="padding-top: 15px;">
                    <form id="urlform" method="post" action="urlsubmit.do">
                        <input type="hidden" name="form_action" value="newurl"/>
                        <input class="longUrlText" name="longUrl" placeholder="Introduce your long URL" />
                        <input type="submit" name="enter_button" value="Enter" />
                        <a href="index.do" class="button">Cancel</a>

                    </form>
                </div>
                <herror>
                    <%
                        if (request.getAttribute("resultMessage") != null) {
                            out.print(request.getAttribute("resultMessage"));
                        }
                    %>
                </herror>
            </div>
        </div>
        <div id="footerfix">
            <%@ include file="footer.html"%>
        </div>
    </body>
</html>
