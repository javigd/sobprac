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
        <div id="page-wrap">
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
            <pre>
                <%
                    if (request.getAttribute("resultMessage") != null) {
                        out.print(request.getAttribute("resultMessage"));
                    }
                %>
            </pre>
            <br>
            <div class="testboxUrl">

                <form id="urlform" method="post" action="urlconf.do">
                    <input type="hidden" name="form_action" value="urlconf" />
                    <table>
                        <tr>
                            <td width="20%">Your Long URL:</td>
                            <td width="80%"> 
                                <input class="longUrlText" type="text" name="longUrl" value="<%=request.getAttribute("longUrl")%>" />
                            </td>
                        </tr>
                        <tr>
                            <td >Your proposed short URL:</td>
                            <td >
                                <span>
                                    <%=request.getAttribute("prefix")%><input class="longUrlText" type="text" name="shortUrl" value="<%=request.getAttribute("shortUrl")%>" />
                                </span>
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
            </div>
            <a href="index.do">Cancel</a>
        </div>  
        <div id="footerfix">
            <%@ include file="footer.html"%>
        </div>
    </body>
</html>
