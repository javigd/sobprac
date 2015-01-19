/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.persistence.IUrlHandler;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author javigd
 */
public class AjaxShortUrlCommand implements Command {

    private final IUrlHandler dbUrlHandler;

    public AjaxShortUrlCommand(IUrlHandler dbHandler) {
        dbUrlHandler = dbHandler;
    }

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // 1 process the request
        String shortUrl = request.getParameter("shortUrl");

        try {
            // 2. Check uer in database
            dbUrlHandler.getUrlFromShort(shortUrl);
            response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write("<valid>true</valid>");
        } catch (SOBException ex) {
            response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write("<valid>false</valid>");
        }
    }
}
