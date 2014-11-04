<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="soburl" class="cat.urv.deim.sob.models.SOBUrl" scope="request" />
<jsp:setProperty name="soburl" property="*" />

<html>
    <head>
        <title>GoShort! | Your URLs</title>
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
        <form id="urlform" method="post" action="urlsubmit.do">
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
    </body>
</html>
