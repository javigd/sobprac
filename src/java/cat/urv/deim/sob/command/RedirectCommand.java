/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.persistence.IUrlHandler;
import cat.urv.deim.sob.util.Config;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        String redirUrl = "";
        try {
            //2. Process the request
            redirUrl = dbUrlHandler.visit(reqUrl);
            response.sendRedirect(redirUrl);
        } catch (SOBException ex) {
            request.setAttribute("resultMessage", ex.getError().getMessage());
            request.setAttribute("prefix", Config.SERVER_REDIR_PREFIX);
            request.setAttribute("shortUrl", reqUrl.replace("/", ""));
            // 3. produce the view with the web result
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/badurl.jsp").forward(request, response);
        }
        System.out.println(reqUrl + " redirecting to " + redirUrl);
    }
}
