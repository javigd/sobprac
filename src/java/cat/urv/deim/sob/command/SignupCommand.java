package cat.urv.deim.sob.command;

import cat.urv.deim.sob.exceptions.SOBError;
import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.models.SOBUser;
import cat.urv.deim.sob.persistence.SOBUserPersistenceAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignupCommand implements Command {
    SOBUserPersistenceAdapter db = new SOBUserPersistenceAdapter();
    
    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        // forward back to jsp if something went wrong
        SOBError error = (SOBError) request.getAttribute("error");
        if (error != null) {
            request.setAttribute("errorMessage", error.getMessage());
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/signup.jsp").forward(request, response);
        }
        
        // 1 process the request
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("encryptedPassword");
        
        SOBUser user = new SOBUser(null, username, email, password);
        
        try {
            // 1.1 Save user to Database
            db.newUser(user);
        } catch (SOBException ex) {
            Logger.getLogger(SignupCommand.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", ex.getMessage());
        }
        
        // 2. produce the view with the web result
        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/signup.jsp").forward(request, response);
    }
}
