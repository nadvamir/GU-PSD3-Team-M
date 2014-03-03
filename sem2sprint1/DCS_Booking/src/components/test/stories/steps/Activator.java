package components.test.stories.steps;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import components.database.CourseAdd;
import components.database.CourseQuery;
import components.database.SessionAdd;
import components.database.SessionQuery;
import components.database.TSAdd;
import components.database.TSQuery;
import components.database.UserAdd;
import components.database.UserQuery;
import components.application.Application;

public class Activator implements BundleActivator {
	
	private AllTheSteps steps;
	
	@Override
	public void start(BundleContext context) throws Exception {
		
		ServiceReference<TSQuery> TSQueryReference = context.getServiceReference(TSQuery.class);
		ServiceReference<TSAdd> TSAddReference = context.getServiceReference(TSAdd.class);
		ServiceReference<SessionQuery> sessionQueryReference = context.getServiceReference(SessionQuery.class);
		ServiceReference<SessionAdd> sessionAddReference = context.getServiceReference(SessionAdd.class);
		ServiceReference<CourseQuery> courseQueryReference = context.getServiceReference(CourseQuery.class);
		ServiceReference<CourseAdd> courseAddReference = context.getServiceReference(CourseAdd.class);
		ServiceReference<UserQuery> userQueryReference = context.getServiceReference(UserQuery.class);
		ServiceReference<UserAdd> userAddReference = context.getServiceReference(UserAdd.class);

		ServiceReference<Application> applicationReference = context.getServiceReference(Application.class);
		
		TSQuery tsquery = context.getService(TSQueryReference);
		TSAdd tsadd = context.getService(TSAddReference);
		SessionQuery sessionquery = context.getService(sessionQueryReference);
		SessionAdd sessionadd = context.getService(sessionAddReference);
		CourseQuery coursequery = context.getService(courseQueryReference);
		CourseAdd courseadd = context.getService(courseAddReference);
		UserQuery userquery = context.getService(userQueryReference);
		UserAdd useradd = context.getService(userAddReference);

		Application app = context.getService(applicationReference);
		
		System.out.println("Setting up test service");

    // a hack to get components inside of the test suite
    // I guess the tests have to be run within OSGi
    // which makes sense, since these are acceptence tests, not unit tests.
    AllTheSteps.context = context;
    AllTheSteps.tsquery = tsquery;
    AllTheSteps.tsadd = tsadd;
    AllTheSteps.sessionquery = sessionquery;
    AllTheSteps.sessionadd = sessionadd;
    AllTheSteps.coursequery = coursequery;
    AllTheSteps.courseadd = courseadd;
    AllTheSteps.userquery = userquery;
    AllTheSteps.useradd = useradd;

    AllTheSteps.app = app;
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		
	}

}
