/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob;

import cat.urv.deim.sob.command.Command;
import cat.urv.deim.sob.persistence.ConnectionPool;
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
public abstract class SOBController extends HttpServlet {
    protected final Map commands = new HashMap();
    protected String DEFAULT_OPERATION;
    protected static ConnectionPool pool;

    protected void setDefaultOperation (String DEFAULT_OPERATION) {
        this.DEFAULT_OPERATION = DEFAULT_OPERATION;
    }

    protected void processCommand(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // 1. choose action
        String formAction = request.getParameter("form_action");
        
        if (null == formAction) {
            formAction = DEFAULT_OPERATION;
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
