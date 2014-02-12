package components.application.handlers;

import components.application.Handler;
import components.login.User;

public class LoginHandler extends Handler {
    private Login handler;

    public LoginHandler(Login h, User.Type... allowed) {
        super(allowed);
        handler = h;
    }

    public void run(User u, String... args) throws Exception {
        checkAccess(u);
        handler.loginCheck(args[0], args[1]);
    }
}
