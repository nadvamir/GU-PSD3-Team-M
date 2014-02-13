package components.coursemanager;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private CourseEditorImpl courseEditor;
	
	private ServiceRegistration<CourseEditor> courseEditorRegistration;

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("hello 2");
		
		courseEditor = new CourseEditorImpl(); 
		
		courseEditorRegistration = context.registerService(
				CourseEditor.class, courseEditor, null);	
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		courseEditorRegistration.unregister();
		
	}
	

}
