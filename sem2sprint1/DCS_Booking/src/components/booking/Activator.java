package components.booking;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private BookingHandler handler;
	private ServiceRegistration<Booker> bookingHandlerRegistration;
	
	@Override
	public void start(BundleContext context) throws Exception {
		
		/* GET ACCESS TO DATABASE
		 * 
		serviceReference<DBMS> serviceReference = context.getServiceReference(DBMS.class);
		
		DBMS dbms = context.getService(serviceReference)
		 */
		
		handler = new BookingHandler();
		// handler = new BookingHandler(dbms);

		bookingHandlerRegistration = context.registerService(Booker.class, handler, null);
		
		System.out.println("Booking service has started");
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		
		bookingHandlerRegistration.unregister();
		
		System.out.println("Booking service has stopper");
		
	}

}
