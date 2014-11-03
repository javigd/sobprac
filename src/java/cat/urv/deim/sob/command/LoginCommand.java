/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.handlers.FormHandler;
import cat.urv.deim.sob.models.SOBUser;
import cat.urv.deim.sob.persistence.ConnectionPool;
import cat.urv.deim.sob.persistence.IUserHandler;
import cat.urv.deim.sob.persistence.SOBUserHandler;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author javigd
 */
public class LoginCommand implements Command {

    private final IUserHandler dbUsrHandler;
    
    public LoginCommand (IUserHandler dbHandler) {
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
        String passwordRepeat = request.getParameter("passwordRepeat");

        // 1.1 Validate form
        FormHandler fh = new FormHandler(null, email, password, passwordRepeat);

        try {
            fh.validateLogin();
            // TODO: Encrypt password on the client-side!
            String encryptedPassword = new String(fh.encryptPassword());
            // 2. Check uer in database
            SOBUser user = new SOBUser(null, null, email, encryptedPassword);
            dbUsrHandler.login(user);
        } catch (SOBException ex) {
            request.setAttribute("errorMessage", ex.getError().getMessage());
        }

        // 3. produce the view with the web result
        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
