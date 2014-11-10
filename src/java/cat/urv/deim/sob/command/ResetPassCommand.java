/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import cat.urv.deim.sob.beans.FormHandler;
import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.models.SOBUser;
import cat.urv.deim.sob.persistence.IUserHandler;
import cat.urv.deim.sob.session.SOBCookie;
import cat.urv.deim.sob.util.Config;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author javigd
 */
public class ResetPassCommand implements Command {

    private final IUserHandler dbUsrHandler;

    public ResetPassCommand(IUserHandler dbHandler) {
        dbUsrHandler = dbHandler;
    }

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // 1 process the request
        String userId = request.getParameter("uid");
        String ticket = request.getParameter("ticket");
        System.out.println("Data rcvd from GET: uid " + userId + " : ticket " + ticket);
        String password = request.getParameter("password");
        String passwordRepeat = request.getParameter("passwordRepeat");

        // 1.1 Validate form
        FormHandler fh = new FormHandler(null, null, password, passwordRepeat);

        try {
            fh.validateReset();
            // Encrypt password
            String encryptedPassword = new String(fh.encryptPassword());
            // 2. Check ticket in database
            SOBUser u = dbUsrHandler.resetPassword(userId, ticket, encryptedPassword);
            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(Config.SESSION_MAX_TIME);
            session.setAttribute("user", u.getUsername());
            session.setAttribute("userid", userId);
            Cookie userCookie = new SOBCookie("user", u.getUsername());
            response.addCookie(userCookie);
            request.setAttribute("form_action", null);
            request.setAttribute("action", null);
            response.sendRedirect("index.do");
        } catch (SOBException ex) {
            // 3. produce the view with the web result
            request.setAttribute("responseMessage", ex.getError().getMessage());
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/resetpass.jsp").forward(request, response);
        }
    }

}
