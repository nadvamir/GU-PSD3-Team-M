package uk.ac.glasgow.psd3.components.booking;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import uk.ac.glasgow.psd3.components.database.CourseQuery;
import uk.ac.glasgow.psd3.components.database.SessionQuery;
import uk.ac.glasgow.psd3.components.database.TSQuery;
import uk.ac.glasgow.psd3.components.database.UserQuery;


public class Activator implements BundleActivator {

	private BookingHandler handler;
	private ServiceRegistration<Booker> bookingHandlerRegistration;
	
	@Override
	public void start(BundleContext context) throws Exception {
		
		
		ServiceReference<TSQuery> TSQueryReference = context.getServiceReference(TSQuery.class);
		ServiceReference<SessionQuery> sessionQueryReference = context.getServiceReference(SessionQuery.class);
		ServiceReference<CourseQuery> courseQueryReference = context.getServiceReference(CourseQuery.class);
		ServiceReference<UserQuery> userQueryReference = context.getServiceReference(UserQuery.class);
		
		TSQuery tsquery = context.getService(TSQueryReference);
		SessionQuery sessionquery = context.getService(sessionQueryReference);
		CourseQuery coursequery = context.getService(courseQueryReference);
		UserQuery userquery = context.getService(userQueryReference);
		
		handler = new BookingHandler(coursequery, sessionquery, tsquery, userquery);

		bookingHandlerRegistration = context.registerService(Booker.class, handler, null);
		
		System.out.println("Booking service has started");
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		
		bookingHandlerRegistration.unregister();
		
		System.out.println("Booking service has stopper");
		
	}

}
