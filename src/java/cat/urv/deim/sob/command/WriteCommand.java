package cat.urv.deim.sob.command;

import cat.urv.deim.sob.models.SOBUser;
import cat.urv.deim.sob.persistence.DatabaseSOBHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import java.io.IOException;

public class WriteCommand implements Command {
    DatabaseSOBHandler db = new DatabaseSOBHandler();
    
    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // 1 process the request
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        SOBUser user = new SOBUser(null, firstName, lastName, email, phone);
        
        //TODO: Remove line below
        request.setAttribute("user", user);

        // 1.1 Save user to Database
        db.newUser(user);
        
        // 2. produce the view with the web result
        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/view.jsp").forward(request, response);
    }
}
