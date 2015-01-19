package cat.urv.deim.sob.command;

import cat.urv.deim.sob.beans.UrlBean;
import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.persistence.IUrlHandler;
import cat.urv.deim.sob.util.Config;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import java.io.IOException;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class NewUrlCommand implements Command {

    private final IUrlHandler dbUrlHandler;

    public NewUrlCommand(IUrlHandler dbHandler) {
        dbUrlHandler = dbHandler;
    }

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String fw = "urlconf";

        UrlBean url = new UrlBean();

        // 1 process the request
        String longUrl = request.getParameter("longUrl");

        // 1.1 Validate form
        url.setLongUrl(longUrl);
        Long usrId = Long.parseLong((String) request.getSession().getAttribute("userid"));
        url.setUserId(usrId);
        
        String userId = url.getUserId().toString();

        try {
            url.validate();
            // 2. Shorten long URL
            /* Call the REST web service method in order to shorten the long URL */
            Client client = ClientBuilder.newClient();
            //TODO: change sobpracsvces to sobprac
            WebTarget target = client.target("http://localhost:8080/sobpracsvces/webresources/shorten?longUrl=" + longUrl + "&userId=" + userId);
            String shortenedURL = target.request(MediaType.TEXT_PLAIN).get(String.class);
            //String shortenedURL = dbUrlHandler.getShortenedUrl(url);
            request.setAttribute("longUrl", longUrl);
            request.setAttribute("shortUrl", shortenedURL);
            request.setAttribute("prefix", Config.SERVER_REDIR_PREFIX);
        } catch (SOBException ex) {
            fw = "newurl";
            request.setAttribute("resultMessage", ex.getError().getMessage());
        }

        // 3. produce the view with the web result
        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/" + fw + ".jsp").forward(request, response);
    }
}
