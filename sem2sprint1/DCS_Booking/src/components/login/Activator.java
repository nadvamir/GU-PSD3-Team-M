package components.login;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator{
	
	private Authenticator authenticator;
	
	@Override
	public void start(BundleContext context)
		throws Exception {
		
		authenticator = new Authenticator();
		
		authenticatorRegistration = context.registerService(Login.class, authenticator, null);
	}	

	@Override
	public void stop(BundleContext context)
		throws Exception {
		
		authenticatorRegistration.unregister();
	}
	

}
