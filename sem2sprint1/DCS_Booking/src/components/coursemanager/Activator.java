package components.coursemanager;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
		
	private ServiceRegistration<CourseEditor> 
		courseEditorRegistration;

	@Override
	public void start(BundleContext context) throws Exception {
		
		CourseEditorHandler courseEditorHandler = new CourseEditorHandler(/*courseManager*/); 
		
		courseEditorRegistration = context.registerService(
				CourseEditor.class, courseEditorHandler, null);	
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		courseEditorRegistration.unregister();
		
	}
	

}
