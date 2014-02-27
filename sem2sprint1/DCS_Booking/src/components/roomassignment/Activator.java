package components.roomassignment;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private TimetableSlotManagerImpl slotManager;
	
	private ServiceRegistration<TimetableSlotManager> slotManagerRegistration;

	@Override
	public void start(BundleContext context) throws Exception {
		
	//	slotManager = new TimetableSlotManagerImpl(); 
		
		slotManagerRegistration = context.registerService(
				TimetableSlotManager.class, slotManager, null);	
		
		System.out.println("Timetable Slot manager service has started");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		slotManagerRegistration.unregister();
		
		System.out.println("Timetable Slot manager service has stopped");
		
	}
	

}
