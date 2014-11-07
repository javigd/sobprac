/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob;

import cat.urv.deim.sob.command.Command;
import cat.urv.deim.sob.command.RedirectCommand;
import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.persistence.ConnectionPool;
import cat.urv.deim.sob.persistence.IUrlHandler;
import cat.urv.deim.sob.persistence.SOBUrlHandler;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author javigd
 */
public class RedirectController extends HttpServlet {
    protected Command command;
    protected static ConnectionPool pool;
    private static IUrlHandler dbUrlHandler;

    @Override
    public void init() {
        /* Initialize a new Connection pool */
        pool = new ConnectionPool();
        /* Get a new URL Handler for URL management */
        dbUrlHandler = new SOBUrlHandler(pool);
        /* Initialize a new RedirectCommand */
        this.command = new RedirectCommand(dbUrlHandler);
    }

    protected void processCommand(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        if (null == command) {
            throw new IllegalArgumentException(
                    "No command available");
        }

        // 3. run the command
        command.execute(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        processCommand(request, response);
    }

    @Override
    public void destroy() {
        if (pool.isOpen()) {
            Logger.getLogger(SOBController.class.getName()).log(Level.INFO, "Attempting to close connection factory...");
            try {
                pool.closeConnectionFactory();
            } catch (SOBException ex) {
                Logger.getLogger(SOBController.class.getName()).log(Level.INFO, ex.getError().getMessage());
            }
            Logger.getLogger(SOBController.class.getName()).log(Level.INFO, "Persistence connection factory was successfully closed.");
        }
    }
}

