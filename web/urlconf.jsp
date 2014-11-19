<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="urlbean" class="cat.urv.deim.sob.beans.UrlBean" scope="request" />
<jsp:setProperty name="urlbean" property="*" />

<html>
    <head>
        <title>GoShort! | New URL</title>
        <link href="design/ion/css/style.css" type="text/css" rel="stylesheet">
        <link href="design/Form/formcss.css" type="text/css" rel="stylesheet">
        <link rel="shortcut icon" href="img/favicon.png" />

    </head>
    <body>
        <%@ include file="header.jsp"%>
        <div id="page-wrap">
            <herror>
                <%                //allow access only if session exists
                    String user = null;
                    if (session.getAttribute("user") == null) {
                        response.sendRedirect("login.jsp");
                    } else {
                        user = (String) session.getAttribute("user");
                    }
                %>


                <%
                    if (request.getAttribute("resultMessage") != null) {
                        out.print(request.getAttribute("resultMessage"));
                    }
                %>
            </herror>
            <br>
            <div class="testboxUrl">

                <form id="urlform" method="post" action="urlconf.do">
                    <input type="hidden" name="form_action" value="urlconf" />
                    <table>
                        <tr>
                            <td width="20%">Your Long URL:</td>
                            <td width="80%"> 
                                <div>
                                    <input style="width: 100%" type="text" name="longUrl" value="<%=request.getAttribute("longUrl")%>" />
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Your proposed short URL:</td>
                            <td>
                               <%=request.getAttribute("prefix")%><input style="width: 50%" type="text" name="shortUrl" value="<%=request.getAttribute("shortUrl")%>" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input type="submit" name="enter_button" value="Enter" />
                                <a href="index.do" class="button">Cancel</a>    
                            </td>

                        </tr>
                    </table>
                </form>
            </div>

        </div>  
        <div id="footerfix">
            <%@ include file="footer.html"%>
        </div>
    </body>
</html>
