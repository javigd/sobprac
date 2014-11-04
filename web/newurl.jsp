<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="soburl" class="cat.urv.deim.sob.models.SOBUrl" scope="request" />
<jsp:setProperty name="soburl" property="*" />

<html>
    <head>
        <title>GoShort!</title>
    </head>
    <body>
        <h2>Shorten your URL</h2>
        <form id="urlform" method="post" action="url/urlsubmit.do">
            <input type="hidden" name="form_action" value="newurl" />
            <table>
                <tr>
                    <td>Your Long URL:</td>
                    <td> 
                        <input type="text" name="longUrl" />
                    </td>
                </tr>
                <tr>
                    <td>Your e-mail:</td>
                    <td>
                        <input type="text" name="useremail" />
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
        <pre>
            <%
                if (request.getAttribute("resultMessage") != null) {
                    out.print(request.getAttribute("resultMessage"));
                }
            %>
        </pre>
        <br>
    </body>
</html>
