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
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.Map;
import java.util.HashMap;

public class ControllerServlet extends HttpServlet {

    private Map commands = new HashMap();
    private static ConnectionPool pool;
    private static IUserHandler dbUsrHandler;
    private static IUrlHandler dbUrlHandler;

    @Override
    public void init() {
        /* Initialize a new Connection pool */
        pool = new ConnectionPool();
        /* Get a new Database User Handler */
        dbUsrHandler = new SOBUserHandler(pool);
        /* Get a new Database URL Handler */
        dbUrlHandler = new SOBUrlHandler(pool);
        
        // list of commands
        this.commands.put("signupinit", new SignupInitCommand());
        this.commands.put("signup", new SignupCommand(dbUsrHandler));
        this.commands.put("logininit", new LoginInitCommand());
        this.commands.put("login", new LoginCommand(dbUsrHandler));
        this.commands.put("newurl", new NewUrlCommand(dbUrlHandler));
    }

    protected void processCommand(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // 1. choose action
        String formAction = request.getParameter("form_action");
        
        if (null == formAction) {
            formAction = "newurl";
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
