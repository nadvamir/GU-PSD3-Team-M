package components.database;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private DBMS dbms;
	private UserAdd add;
	private UserQuery query;
	
	private ServiceRegistration<UserAdd> userAddRegistration;
	
	private ServiceRegistration<UserQuery> userQueryRegistration;
	
	@Override
	public void start(BundleContext context)
		throws Exception {
		
		dbms = new DBMS();
		
		UserHandler handler = new UserHandler(dbms);
		
		add = handler;
		query = handler;
		
		userAddRegistration = context.registerService(UserAdd.class, add, null);	
		
		userQueryRegistration = context.registerService(UserQuery.class, query, null);	
	}	

	@Override
	public void stop(BundleContext context)	throws Exception {
		dbms.stop();
		userAddRegistration.unregister();
		userQueryRegistration.unregister();
		
	}
}
