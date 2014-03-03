package uk.ac.glasgow.psd3.components.coursemanager;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import uk.ac.glasgow.psd3.components.database.DBMS;
import uk.ac.glasgow.psd3.components.mcwrapper.MyCampusWrapper;


public class Activator implements BundleActivator {

	private CourseManagerImpl courseManager;
	private DBMS dbms;
	
	private ServiceRegistration<CourseManager> courseEditorRegistration;

	@Override
	public void start(BundleContext context) throws Exception {
		
		ServiceReference<MyCampusWrapper> mcReference =
                context.getServiceReference(MyCampusWrapper.class);
		MyCampusWrapper mcController = context.getService(mcReference);
		
		
		courseManager = new CourseManagerImpl(mcController); 
		
		courseEditorRegistration = context.registerService(
				CourseManager.class, courseManager, null);	
		
		System.out.println("Course manager service has started");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		courseEditorRegistration.unregister();
		
		System.out.println("Course manager service has stopped");
		
	}
	

}
