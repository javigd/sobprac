package cat.urv.deim.sob;

import cat.urv.deim.sob.command.NewUrlCommand;
import cat.urv.deim.sob.persistence.ConnectionPool;
import cat.urv.deim.sob.persistence.IUrlHandler;
import cat.urv.deim.sob.persistence.SOBUrlHandler;
import cat.urv.deim.sob.util.Config;

public class UrlController extends SOBController {

    private static IUrlHandler dbUrlHandler;

    @Override
    public void init() {
        /* Set Default Operation for this Controller */
        setDefaultOperation(Config.DEFAULT_OP_URL);
        /* Initialize a new Connection pool */
        pool = new ConnectionPool();
        /* Get a new Database URL Handler */
        dbUrlHandler = new SOBUrlHandler(pool);
        // list of commands
        this.commands.put("newurl", new NewUrlCommand(dbUrlHandler));
    }
}
