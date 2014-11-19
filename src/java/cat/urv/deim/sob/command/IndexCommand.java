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
import cat.urv.deim.sob.util.Config;
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

        // Get the page value, set it as default if no page has been specified
        int page;
        String pageParam = request.getParameter("page");
        if (pageParam == null) {
            page = 1;
        } else {
            page = Integer.parseInt(pageParam);
        }

        if (userId != null) {
            try {
                // Compute the number of pages (user urls / urls per page)
                int urlcount = dbUrlHandler.getUrlCountByUser(userId);
                int npages = urlcount / Config.DEFAULT_URL_SET_SIZE;
                if (urlcount % Config.DEFAULT_URL_SET_SIZE > 0) npages ++;
                // In case the requesting page is invalid (hardcoded GETs), display message.
                if (page < 1 || page > npages) throw new SOBException(SOBError.INVALID_URL_PAGE);
                // Get a set of URLs to show, given the page
                List<UrlBean> urls = dbUrlHandler.getUserUrlsByPage(userId, page - 1);
                // Set the proper attributes for the view
                request.setAttribute("npages", npages);
                request.setAttribute("currentPage", page);
                request.setAttribute("showPages", Config.DEFAULT_URL_PAGES);
                request.setAttribute("loadedUrls", urls);
                request.setAttribute("prefix", Config.SERVER_REDIR_PREFIX);
            } catch (SOBException ex) {
                request.setAttribute("responseMessage", ex.getError().getMessage());
            }
        }

        // 3. produce the view with the web result
        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/index.jsp").forward(request, response);

    }
}
