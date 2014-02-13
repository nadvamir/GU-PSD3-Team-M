package components.application;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import components.application.handlers.*;
import components.coursemanager.CourseEditor;
import components.login.Login;
import components.database.User;

public class Activator implements BundleActivator {
    private ApplicationImpl app;

    private ServiceRegistration<Application> appRegistration;
    
    @Override
    public void start(BundleContext context) throws Exception {
        // creating the application
        app = new ApplicationImpl();
        appRegistration = context.registerService(
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
        try {
			app.registerHandler(
			    "login", new LoginHandler(loginRH, User.Type.GUEST)
			);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        ServiceReference<CourseEditor> courseEditorReference =
                context.getServiceReference(CourseEditor.class);
        CourseEditor courseEditorService = context.getService(courseEditorReference);
		try {
			app.registerHandler(
				    "courseEditor", new CourseEditorHandler(courseEditorService, User.Type.GUEST)
			);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
            
    }
}
