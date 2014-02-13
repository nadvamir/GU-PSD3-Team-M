package components.database;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private DBMS dbms;
	private UserAdd addU;
	private UserQuery queryU;
	private CourseAdd addC;
	private CourseQuery queryC;
	private SessionAdd addS;
	private SessionQuery queryS;
	
	private ServiceRegistration<UserAdd> userAddRegistration;
	
	private ServiceRegistration<UserQuery> userQueryRegistration;
	
	private ServiceRegistration<CourseAdd> courseAddRegistration;
	
	private ServiceRegistration<CourseQuery> courseQueryRegistration;	
	
	private ServiceRegistration<SessionAdd> sessionAddRegistration;
	
	private ServiceRegistration<SessionQuery> sessionQueryRegistration;
	
	@Override
	public void start(BundleContext context)
		throws Exception {
		
		dbms = new DBMS();
		
		UserHandler handler = new UserHandler(dbms);
		
		addU = handler;
		queryU = handler;
		
		userAddRegistration = context.registerService(UserAdd.class, addU, null);	
		
		userQueryRegistration = context.registerService(UserQuery.class, queryU, null);
		
		courseAddRegistration = context.registerService(CourseAdd.class, addC, null);	
		
		courseQueryRegistration = context.registerService(CourseQuery.class, queryC, null);
		
		sessionAddRegistration = context.registerService(SessionAdd.class, addS, null);	
		
		sessionQueryRegistration = context.registerService(SessionQuery.class, queryS, null);

		System.out.println("Database service has started");
	}	

	@Override
	public void stop(BundleContext context)	throws Exception {
		dbms.stop();
		userAddRegistration.unregister();
		userQueryRegistration.unregister();
		courseAddRegistration.unregister();
		courseQueryRegistration.unregister();
		sessionAddRegistration.unregister();
		sessionQueryRegistration.unregister();

		System.out.println("Database service has stopped");
	}
}
