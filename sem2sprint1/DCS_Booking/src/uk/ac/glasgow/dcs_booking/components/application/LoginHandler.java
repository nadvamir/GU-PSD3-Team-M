package uk.ac.glasgow.dcs_booking.components.application;

import uk.ac.glasgow.dcs_booking.components.application.Handler;
import uk.ac.glasgow.dcs_booking.components.database.User;
import uk.ac.glasgow.dcs_booking.components.login.Login;

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
