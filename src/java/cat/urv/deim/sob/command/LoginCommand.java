/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.beans.FormHandler;
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
public class LoginCommand implements Command {

    private final IUserHandler dbUsrHandler;

    public LoginCommand(IUserHandler dbHandler) {
        dbUsrHandler = dbHandler;
    }

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // 1 process the request
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // 1.1 Validate form
        FormHandler fh = new FormHandler(null, email, password, null);

        try {
            fh.validateLogin();
            String encryptedPassword = new String(fh.encryptPassword());
            // 2. Check uer in database
            SOBUser user = new SOBUser(null, null, email, encryptedPassword);
            SOBUser loggedUser = dbUsrHandler.doLogin(user);
            String userName = loggedUser.getUsername();
            //set session attributes
            HttpSession session = request.getSession(true);
            session.setAttribute("user", userName);
            session.setAttribute("userid", loggedUser.getId().toString());
            session.setMaxInactiveInterval(Config.SESSION_MAX_TIME);
            Cookie userCookie = new SOBCookie("user", userName);
            response.addCookie(userCookie);
            response.sendRedirect("index.do");
        } catch (SOBException ex) {
            request.setAttribute("responseMessage", ex.getError().getMessage());
        }

        // 3. produce the view with the web result
        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
