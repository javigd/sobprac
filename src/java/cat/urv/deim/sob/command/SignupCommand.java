package cat.urv.deim.sob.command;

import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.handlers.FormHandler;
import cat.urv.deim.sob.models.SOBUser;
import cat.urv.deim.sob.persistence.IUserHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import java.io.IOException;

public class SignupCommand implements Command {

    private final IUserHandler dbUsrHandler;

    public SignupCommand(IUserHandler dbHandler) {
        dbUsrHandler = dbHandler;
    }

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // 1 process the request
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordRepeat = request.getParameter("passwordRepeat");

        // 1.1 Validate form
        FormHandler fh = new FormHandler(username, email, password, passwordRepeat);

        try {
            fh.validate();
            // TODO: Encrypt password on the client-side!
            String encryptedPassword = new String(fh.encryptPassword());
            // 2. Save user to Database
            SOBUser user = new SOBUser(null, username, email, encryptedPassword);
            dbUsrHandler.doSignUp(user);
            request.setAttribute("responseMessage", "Congratulations, " + user.getUsername() + "! You have been successfully signed up!");
        } catch (SOBException ex) {
            request.setAttribute("responseMessage", ex.getError().getMessage());
        }

        // 3. produce the view with the web result
        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/signup.jsp").forward(request, response);
    }
}
