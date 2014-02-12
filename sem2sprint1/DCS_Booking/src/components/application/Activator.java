package components.mcwrapper;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import components.application.handlers.*;
import components.login.Login;
import components.login.User;

public class Activator implements BundleActivator {
    private ApplicationImpl app;

    private ServiceRegistration<Application> appRegistration;
    
    @Override
    public void start(BundleContext context) throws Exception {
        // creating the application
        app = new ApplicationImpl();
        wrapperRegistration = context.registerService(
            Application.class, app, null
        );

        // registering all the handlers
        registerHandlers(context);

        // authorising as a student or whatever really
        app.runCmd("login", "Whatever", "pass of sorts");
    }

    @Override
    public void stop(BundleContext arg0) throws Exception {
        appRegistration.unregister();
    }

    /**
     * Registering all the handlers for the included bundles,
     * so that we can control access to them
     */
    public void registerHandlers(BundleContext context) {
        // registering the login handler
        ServiceReference<Login> loginReference =
            context.getServiceReference(Login.class);
        Login loginRH = context.getService(loginReference);
        app.registerHandler("login", new LoginHandler(loginRH, User.GUEST));
    }
}
