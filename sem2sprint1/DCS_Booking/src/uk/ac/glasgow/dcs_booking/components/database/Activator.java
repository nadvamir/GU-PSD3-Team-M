package uk.ac.glasgow.dcs_booking.components.database;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	//
	private DBMS dbms;
	
	private UserAdd addU;
	private UserQuery queryU;
	
	private TSAdd addTS;
	private TSQuery queryTS;
	
	private SessionAdd addS;
	private SessionQuery queryS;
	
	private CourseAdd addC;
	private CourseQuery queryC;

	//Service registrations
	private ServiceRegistration<UserAdd> userAddRegistration;
	private ServiceRegistration<UserQuery> userQueryRegistration;
	
	private ServiceRegistration<TSAdd> tSAddRegistration;
	private ServiceRegistration<TSQuery> tSQueryRegistration;
	
	private ServiceRegistration<SessionAdd> sessionAddRegistration;	
	private ServiceRegistration<SessionQuery> sessionQueryRegistration;
	
	private ServiceRegistration<CourseAdd> courseAddRegistration;
	private ServiceRegistration<CourseQuery> courseQueryRegistration;	
	

	
	@Override
	public void start(BundleContext context)
		throws Exception {
		
		dbms = new DBMS();
		
		UserHandler userHandler = new UserHandler(dbms);
		
		addU = userHandler;
		queryU = userHandler;
		
		TSHandler tsHandler = new TSHandler(dbms);
		
		addTS = tsHandler;
		queryTS = tsHandler;
		
		SessionHandler sessionHandler = new SessionHandler(dbms);
		
		addS = sessionHandler;
		queryS = sessionHandler;
		
		CourseHandler courseHandler = new CourseHandler(dbms);
		
		addC = courseHandler;
		queryC = courseHandler;
		

		userAddRegistration = context.registerService(UserAdd.class, addU, null);	
		userQueryRegistration = context.registerService(UserQuery.class, queryU, null);
		
		tSAddRegistration = context.registerService(TSAdd.class, addTS, null);	
		tSQueryRegistration = context.registerService(TSQuery.class, queryTS, null);
		
		sessionAddRegistration = context.registerService(SessionAdd.class, addS, null);	
		sessionQueryRegistration = context.registerService(SessionQuery.class, queryS, null);
		
		courseAddRegistration = context.registerService(CourseAdd.class, addC, null);		
		courseQueryRegistration = context.registerService(CourseQuery.class, queryC, null);

		System.out.println("Database service has started");
	}	

	@Override
	public void stop(BundleContext context)	throws Exception {
		dbms.stop();
		
		userAddRegistration.unregister();
		userQueryRegistration.unregister();
		
		tSAddRegistration.unregister();
		tSQueryRegistration.unregister();
		
		sessionAddRegistration.unregister();
		sessionQueryRegistration.unregister();
		
		courseAddRegistration.unregister();
		courseQueryRegistration.unregister();

		System.out.println("Database service has stopped");
	}
}
