package components.application;



public interface Application {
    /**
     * Run a specified command.
     * We need such an interface in order to check,
     * whether the user has access to the particular controller.
     */
    public void runCmd(String cmd, String... args) throws Exception;

    /**
     * Register a handler for a command.
     */
    public void registerHandler(String cmd, Handler h) throws Exception;
}
