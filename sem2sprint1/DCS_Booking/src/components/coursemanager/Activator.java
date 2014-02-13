package components.coursemanager;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private CourseManagerImpl courseEditor;
	
	private ServiceRegistration<CourseManager> courseEditorRegistration;

	@Override
	public void start(BundleContext context) throws Exception {
		
		courseEditor = new CourseManagerImpl(); 
		
		courseEditorRegistration = context.registerService(
				CourseManager.class, courseEditor, null);	
		
		System.out.println("Course manager service has started");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		courseEditorRegistration.unregister();
		
		System.out.println("Course manager service has stopped");
		
	}
	

}
