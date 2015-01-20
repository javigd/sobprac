/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.models.SOBUrl;
import cat.urv.deim.sob.persistence.IUrlHandler;
import cat.urv.deim.sob.util.Config;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author javigd
 */
public class RedirectCommand implements Command {

    private final IUrlHandler dbUrlHandler;

    public RedirectCommand(IUrlHandler dbHandler) {
        dbUrlHandler = dbHandler;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. prepare parameters
        String reqUrl = request.getPathInfo();
        reqUrl = reqUrl.replace("/", "");
        String redirUrl = "";
        try {
            //2. Process the request
            //redirUrl = dbUrlHandler.visit(reqUrl);
            SOBUrl myUrl = dbUrlHandler.getUrlFromShort(reqUrl);
            redirUrl = myUrl.getLongUrl();
            /* Call the REST web service method in order to store the new  URL */
            Client client = ClientBuilder.newClient();
            //TODO: change sobpracsvces to sobprac
            WebTarget target = client.target("http://localhost:8080/sobpracsvces/webresources/url/visit/" + myUrl.getId());
            target.request(MediaType.APPLICATION_JSON).get();
            response.sendRedirect(redirUrl);
        } catch (SOBException ex) {
            request.setAttribute("resultMessage", ex.getError().getMessage());
            request.setAttribute("prefix", Config.SERVER_REDIR_PREFIX);
            request.setAttribute("shortUrl", reqUrl.replace("/", ""));
            // 3. produce the view with the web result
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/badurl.jsp").forward(request, response);
        }
    }
}
