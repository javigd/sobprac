/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob;

import cat.urv.deim.sob.command.LoginCommand;
import cat.urv.deim.sob.command.LoginInitCommand;
import cat.urv.deim.sob.persistence.ConnectionPool;
import cat.urv.deim.sob.persistence.IUserHandler;
import cat.urv.deim.sob.persistence.SOBUserHandler;
import cat.urv.deim.sob.util.Config;

/**
 *
 * @author javigd
 */
public class LoginController extends SOBController {

    private static IUserHandler dbUsrHandler;

    @Override
    public void init() {
        /* Set Default Operation for this Controller */
        setDefaultOperation(Config.DEFAULT_OP_LOGIN);
        /* Initialize a new Connection pool */
        pool = new ConnectionPool();
        /* Get a new Database User Handler */
        dbUsrHandler = new SOBUserHandler(pool);
        
        // list of commands
        this.commands.put("logininit", new LoginInitCommand());
        this.commands.put("login", new LoginCommand(dbUsrHandler));
    }
}

