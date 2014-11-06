/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob;

import cat.urv.deim.sob.command.Command;
import cat.urv.deim.sob.command.ConfUrlCommand;
import cat.urv.deim.sob.command.IndexCommand;
import cat.urv.deim.sob.command.LoginCommand;
import cat.urv.deim.sob.command.LogoutCommand;
import cat.urv.deim.sob.command.NewUrlCommand;
import cat.urv.deim.sob.command.SignupCommand;
import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.persistence.ConnectionPool;
import cat.urv.deim.sob.persistence.IUrlHandler;
import cat.urv.deim.sob.persistence.IUserHandler;
import cat.urv.deim.sob.persistence.SOBUrlHandler;
import cat.urv.deim.sob.persistence.SOBUserHandler;
import cat.urv.deim.sob.util.Config;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
public class SOBController extends HttpServlet {

    protected final Map commands = new HashMap();
    protected static ConnectionPool pool;
    private static IUserHandler dbUsrHandler;
    private static IUrlHandler dbUrlHandler;

    @Override
    public void init() {
        /* Initialize a new Connection pool */
        pool = new ConnectionPool();
        /* Get a new User Handler for User management */
        dbUsrHandler = new SOBUserHandler(pool);
        /* Get a new URL Handler for URL management */
        dbUrlHandler = new SOBUrlHandler(pool);
        // list of commands
        this.commands.put("login", new LoginCommand(dbUsrHandler));
        this.commands.put("signup", new SignupCommand(dbUsrHandler));
        this.commands.put("newurl", new NewUrlCommand(dbUrlHandler));
        this.commands.put("urlconf", new ConfUrlCommand(dbUrlHandler));
        this.commands.put("index", new IndexCommand(dbUrlHandler));
        this.commands.put("logout", new LogoutCommand());
    }

    protected void processCommand(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // 1. choose action
        String action = request.getParameter("action");

        // Execute the corresponding form_action if no action has been defined
        // trigger the default action otherwise
        if (null == action) {
            if (request.getParameter("form_action") != null) {
                action = request.getParameter("form_action");
            } else {
                action = Config.DEFAULT_ACTION;
            }
        }

        // 2. choose related command
        Command command = (Command) commands.get(action);

        if (null == command) {
            throw new IllegalArgumentException(
                    "No command for action: " + action);
        }

        // 3. run the command
        command.execute(request, response);
    }

    @Override
    public void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        processCommand(request, response);
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
