package components.application.handlers;

import components.application.Handler;
import components.login.User;
import components.login.Login;

public class LoginHandler extends Handler {
    private Login handler;

    public LoginHandler(Login h, User.Type... allowed) {
        super(allowed);
        handler = h;
    }

    public void run(User u, String... args) throws Exception {
        checkAccess(u);
        User nu = handler.loginCheck(args[0], args[1]);
        u.setUsername(nu.getUsername());
        u.setType(nu.getType());
    }
}
