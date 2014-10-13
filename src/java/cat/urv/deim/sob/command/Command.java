package cat.urv.deim.sob.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

public interface Command {

    public void execute(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException;
}
