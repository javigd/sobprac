/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import cat.urv.deim.sob.beans.UrlBean;
import cat.urv.deim.sob.exceptions.SOBError;
import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.persistence.IUrlHandler;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author javigd
 */
public class IndexCommand implements Command {
    
    private final IUrlHandler dbUrlHandler;

    public IndexCommand(IUrlHandler dbUrlHandler) {
        this.dbUrlHandler = dbUrlHandler;
    }
    
    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // 1 process the request
        String userId = (String) request.getSession().getAttribute("userid");

        request.setAttribute("responseMessage", null);
        
        try {
            List<UrlBean> urls = dbUrlHandler.getUserUrls(userId);
            request.setAttribute("loadedUrls", urls);
        } catch (SOBException ex) {
            request.setAttribute("responseMessage", SOBError.URLS_NOT_LOADED.getMessage());
        }
        
        // 3. produce the view with the web result
        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/index.jsp").forward(request, response);

    }
}
