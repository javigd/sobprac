/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob;

import cat.urv.deim.sob.command.SignupCommand;
import cat.urv.deim.sob.command.SignupInitCommand;
import cat.urv.deim.sob.persistence.ConnectionPool;
import cat.urv.deim.sob.persistence.IUserHandler;
import cat.urv.deim.sob.persistence.SOBUserHandler;
import cat.urv.deim.sob.util.Config;

/**
 *
 * @author javigd
 */
public class SignupController extends SOBController {

    private static IUserHandler dbUsrHandler;

    @Override
    public void init() {
        /* Set Default Operation for this Controller */
        setDefaultOperation(Config.DEFAULT_OP_SIGNUP);
        /* Initialize a new Connection pool */
        pool = new ConnectionPool();
        /* Get a new Database User Handler */
        dbUsrHandler = new SOBUserHandler(pool);        
        // list of commands
        this.commands.put("signupinit", new SignupInitCommand());
        this.commands.put("signup", new SignupCommand(dbUsrHandler));
    }
}
