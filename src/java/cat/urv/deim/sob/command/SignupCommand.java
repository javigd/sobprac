package cat.urv.deim.sob.command;

import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.beans.FormHandler;
import cat.urv.deim.sob.models.SOBUrl;
import cat.urv.deim.sob.models.SOBUser;
import cat.urv.deim.sob.persistence.IUserHandler;
import cat.urv.deim.sob.session.SOBCookie;
import cat.urv.deim.sob.util.Config;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

        String fw = "/signup.jsp";

        // 1 process the request
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordRepeat = request.getParameter("passwordRepeat");

        // 1.1 Validate form
        FormHandler fh = new FormHandler(username, email, password, passwordRepeat);

        try {
            fh.validate();
            // Encrypt password
            String encryptedPassword = new String(fh.encryptPassword());
            // 2. Save user to Database
            SOBUser user = new SOBUser(null, username, email, encryptedPassword);
            //SOBUser signedUser = dbUsrHandler.doSignUp(user);
            /* Call the REST web service method in order to store the new  URL */
            Client client = ClientBuilder.newClient();
            //TODO: change sobpracsvces to sobprac
            WebTarget target = client.target("http://localhost:8080/sobpracsvces/webresources/user/");
            Response userResponse = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(user, MediaType.APPLICATION_JSON));
            SOBUser signedUser = userResponse.readEntity(SOBUser.class);
            //set session attributes
            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(Config.SESSION_MAX_TIME);
            session.setAttribute("user", username);
            session.setAttribute("userid", signedUser.getId().toString());
            Cookie userCookie = new SOBCookie("user", username);
            response.addCookie(userCookie);
            request.setAttribute("form_action", null);
            request.setAttribute("action", null);
            response.sendRedirect("index.do");
        } catch (SOBException ex) {
            request.setAttribute("responseMessage", ex.getError().getMessage());
            // 3. produce the view with the web result
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher(fw).forward(request, response);
        }
    }
}
