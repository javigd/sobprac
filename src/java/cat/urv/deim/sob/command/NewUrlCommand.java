package cat.urv.deim.sob.command;

import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.models.SOBUrl;
import cat.urv.deim.sob.persistence.IUrlHandler;
import cat.urv.deim.sob.util.Config;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import java.io.IOException;

public class NewUrlCommand implements Command {

    private final IUrlHandler dbUrlHandler;
    
    public NewUrlCommand (IUrlHandler dbHandler) {
        dbUrlHandler = dbHandler;
    }
    
    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        SOBUrl url = new SOBUrl();
        
        // 1 process the request
        String longUrl = request.getParameter("longUrl");
        String email = request.getParameter("useremail");
        
        // 1.1 Validate form
        url.setLongUrl(longUrl);
        url.setUseremail(email);
        
        try {
            url.validate();
            // 2. Shorten and Save url to Database
            String shortenedURL = dbUrlHandler.newUrl(url);
            request.setAttribute("resultMessage", Config.SERVER_PREFIX + shortenedURL);
        } catch (SOBException ex) {
            request.setAttribute("resultMessage", ex.getError().getMessage());
        }

        // 3. produce the view with the web result
        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/newurl.jsp").forward(request, response);
    }
}
