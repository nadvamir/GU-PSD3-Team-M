package uk.ac.glasgow.psd3.components.application;

import java.util.HashMap;

import uk.ac.glasgow.psd3.components.database.User;

public abstract class Handler {
    protected HashMap<User.Type, Boolean> allowedUsers;

    public Handler(User.Type... allowed) {
    	allowedUsers = new HashMap<User.Type, Boolean>();
        for (User.Type u: allowed) {
            allowedUsers.put(u, true);
        }
    }

    public abstract void run(User u, String... args) throws Exception;

    public void checkAccess(User u) throws Exception {
        if (!allowedUsers.containsKey(u.getType())) {
            throw new Exception("403: Forbidden");
        }
    }
}
