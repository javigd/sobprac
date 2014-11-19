<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="formHandler" class="cat.urv.deim.sob.beans.FormHandler" scope="request" />
<jsp:setProperty name="formHandler" property="*" />

<!DOCTYPE html>
<html>
    <head>
        <title>GoShort! | Reset Password</title>
        <link href="design/ion/css/style.css" type="text/css" rel="stylesheet">
    </head>
    <body>
        <%@ include file="header.jsp"%>
        <div id="page-wrap">
            <br>
            <h2>Reset your password</h2>
            <pre>
                <%
                    if (request.getAttribute("responseMessage") != null) {
                        out.print(request.getAttribute("responseMessage"));
                    }
                %>
            </pre>
            <br>
            <form id="resetform" method="post" action="resetpass.do?uid=<%=request.getParameter("uid")%>&ticket=<%=request.getParameter("ticket")%>">
                <input type="hidden" name="form_action" value="resetpass" />
                <table>
                    <tr>
                        <td>
                            Your new password:
                        </td>
                        <td>
                            <input type="password" name="password" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Repeat password:
                        </td>
                        <td>
                            <input type="password" name="passwordRepeat" />
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
        <div id="footerfix">
            <%@ include file="footer.html"%>
        </div>
    </body>
</html>
