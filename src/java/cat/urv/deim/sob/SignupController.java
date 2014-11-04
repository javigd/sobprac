/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob;

import cat.urv.deim.sob.command.Command;
import cat.urv.deim.sob.command.LoginCommand;
import cat.urv.deim.sob.command.LoginInitCommand;
import cat.urv.deim.sob.command.NewUrlCommand;
import cat.urv.deim.sob.command.SignupCommand;
import cat.urv.deim.sob.command.SignupInitCommand;
import cat.urv.deim.sob.persistence.ConnectionPool;
import cat.urv.deim.sob.persistence.IUrlHandler;
import cat.urv.deim.sob.persistence.IUserHandler;
import cat.urv.deim.sob.persistence.SOBUrlHandler;
import cat.urv.deim.sob.persistence.SOBUserHandler;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author javigd
 */
public class SignupController extends HttpServlet {

    private final Map commands = new HashMap();
    private static ConnectionPool pool;
    private static IUserHandler dbUsrHandler;

    @Override
    public void init() {
        /* Initialize a new Connection pool */
        pool = new ConnectionPool();
        /* Get a new Database User Handler */
        dbUsrHandler = new SOBUserHandler(pool);        
        // list of commands
        this.commands.put("signupinit", new SignupInitCommand());
        this.commands.put("signup", new SignupCommand(dbUsrHandler));
    }

    protected void processCommand(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // 1. choose action
        String formAction = request.getParameter("form_action");
        
        if (null == formAction) {
            formAction = "signupinit";
        }

        // 2. choose related command
        Command command = (Command) commands.get(formAction);

        if (null == command) {
            throw new IllegalArgumentException(
                    "No command for form action: " + formAction);
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
        pool.closeConnectionFactory();
    }
}
