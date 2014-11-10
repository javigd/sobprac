package cat.urv.deim.sob.command;

import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.models.SOBUser;
import cat.urv.deim.sob.persistence.IUserHandler;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author javigd
 */
public class ForgotPassCommand implements Command {

    private final IUserHandler dbUsrHandler;

    public ForgotPassCommand(IUserHandler dbHandler) {
        dbUsrHandler = dbHandler;
    }

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // 1 process the request
        String email = request.getParameter("email");
        
        try {
            // 2. Generate, save and get a ticket
            SOBUser u = dbUsrHandler.rememberPassword(email);
            //TODO: Generate the URL with u.ticket + u.id and send e-mail to user
            response.sendRedirect("login.jsp");
        } catch (SOBException ex) {
            // 3. produce the view with the web result
            request.setAttribute("responseMessage", ex.getError().getMessage());
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/iforgot.jsp").forward(request, response);
        }
    }
}
