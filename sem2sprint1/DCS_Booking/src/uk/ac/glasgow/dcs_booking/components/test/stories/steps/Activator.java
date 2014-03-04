package uk.ac.glasgow.dcs_booking.components.test.stories.steps;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import uk.ac.glasgow.dcs_booking.components.application.Application;
import uk.ac.glasgow.dcs_booking.components.database.CourseAdd;
import uk.ac.glasgow.dcs_booking.components.database.CourseQuery;
import uk.ac.glasgow.dcs_booking.components.database.SessionAdd;
import uk.ac.glasgow.dcs_booking.components.database.SessionQuery;
import uk.ac.glasgow.dcs_booking.components.database.TSAdd;
import uk.ac.glasgow.dcs_booking.components.database.TSQuery;
import uk.ac.glasgow.dcs_booking.components.database.UserAdd;
import uk.ac.glasgow.dcs_booking.components.database.UserQuery;


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
