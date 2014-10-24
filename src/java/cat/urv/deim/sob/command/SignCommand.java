package cat.urv.deim.sob.command;

import cat.urv.deim.sob.models.SOBUser;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import java.io.IOException;

public class SignCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // 1. process the request
        //TODO: Remove attribute management of users below, store in DB instead        
        request.setAttribute("user", new SOBUser());
        
        request.setAttribute("error", "");
        
        // 2. produce the view with the web result
        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/signin.jsp").forward(request, response);
    }
}
