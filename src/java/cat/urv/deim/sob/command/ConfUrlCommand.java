/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import cat.urv.deim.sob.beans.UrlBean;
import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.persistence.IUrlHandler;
import cat.urv.deim.sob.util.Config;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author javigd
 */
public class ConfUrlCommand implements Command {

    private final IUrlHandler dbUrlHandler;

    public ConfUrlCommand(IUrlHandler dbHandler) {
        dbUrlHandler = dbHandler;
    }

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        UrlBean url = new UrlBean();

        // 1 process the request
        String longUrl = request.getParameter("longUrl");
        String shortUrl = request.getParameter("shortUrl");

        // 1.1 Validate form
        url.setLongUrl(longUrl);
        Long usrId = Long.parseLong((String) request.getSession().getAttribute("userid"));
        url.setUserId(usrId);
        url.setShortUrl(shortUrl);

        try {
            url.validate();
            // 2. Shorten and Save url to Database
            dbUrlHandler.newUrl(url);
            request.setAttribute("form_action", null);
            request.setAttribute("action", null);
            response.sendRedirect("index.do");
        } catch (SOBException ex) {
            request.setAttribute("longUrl", longUrl);
            request.setAttribute("shortUrl", shortUrl);
            request.setAttribute("prefix", Config.SERVER_PREFIX);
            request.setAttribute("resultMessage", ex.getError().getMessage());
            // 3. produce the view with the web result
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/urlconf.jsp").forward(request, response);
        }
    }
}
