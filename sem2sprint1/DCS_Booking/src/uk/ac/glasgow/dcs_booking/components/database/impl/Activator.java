package uk.ac.glasgow.dcs_booking.components.database.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import uk.ac.glasgow.dcs_booking.components.database.CourseAdd;
import uk.ac.glasgow.dcs_booking.components.database.CourseQuery;
import uk.ac.glasgow.dcs_booking.components.database.DBMS;
import uk.ac.glasgow.dcs_booking.components.database.SessionAdd;
import uk.ac.glasgow.dcs_booking.components.database.SessionQuery;
import uk.ac.glasgow.dcs_booking.components.database.TSAdd;
import uk.ac.glasgow.dcs_booking.components.database.TSQuery;
import uk.ac.glasgow.dcs_booking.components.database.UserAdd;
import uk.ac.glasgow.dcs_booking.components.database.UserQuery;

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
		
		TSHandler tsHandler = new TSHandler(dbms, queryU);
		
		addTS = tsHandler;
		queryTS = tsHandler;
		
		SessionHandler sessionHandler = new SessionHandler(dbms,queryTS);
		
		addS = sessionHandler;
		queryS = sessionHandler;
		
		CourseHandler courseHandler = new CourseHandler(dbms, queryS);
		
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
