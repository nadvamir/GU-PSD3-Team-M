package components.application;

import java.util.HashMap;
import components.login.User;

public interface ApplicationImpl implements Application {
    private HashMap<String, Handler> handlers;
    private User user;

    public Application() {
        handlers = new HashMap<String, Handler> ();
        user = new User("guest", User.GUEST);
    }

    /**
     * Run a specified command.
     */
    public void runCmd(String cmd, String... args) throws Exception {
        Handler h = handlers.get(cmd);
        if (h == nul)
            throw new Exception("No such handler found");
        h.run(user, args);
    }

    /**
     * Register a handler for a command
     */
    public void registerHandler(String cmd, Handler h) {
        if (handlers.get(cmd) != null)
            throw new Exception("Trying to overwrite old handler");
        handlers.put(cmd, h);
    }
}
