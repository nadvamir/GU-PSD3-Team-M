package components.application;

import java.util.HashMap;
import components.database.User;

public class ApplicationImpl implements Application {
    private HashMap<String, Handler> handlers;
    private User user;

    public ApplicationImpl() {
        handlers = new HashMap<String, Handler> ();
        user = new User("guest", User.Type.GUEST);
    }

    /**
     * Run a specified command.
     */
    public void runCmd(String cmd, String... args) throws Exception {
        Handler h = handlers.get(cmd);
        if (h == null)
            throw new Exception("No such handler found");
        h.run(user, args);
    }

    /**
     * Register a handler for a command
     */
    public void registerHandler(String cmd, Handler h) throws Exception {
        if (handlers.get(cmd) != null)
            throw new Exception("Trying to overwrite old handler");
        handlers.put(cmd, h);
    }
}
