package components.application;

import components.login.User;

public abstract class Handler {
    protected HashMap<User.Type, boolean> allowedUsers;

    public Handler(User.Type... allowed) {
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
