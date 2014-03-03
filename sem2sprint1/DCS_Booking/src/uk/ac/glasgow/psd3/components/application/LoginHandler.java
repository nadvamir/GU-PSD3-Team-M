package uk.ac.glasgow.psd3.components.application;

import uk.ac.glasgow.psd3.components.application.Handler;
import uk.ac.glasgow.psd3.components.database.User;
import uk.ac.glasgow.psd3.components.login.Login;

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
