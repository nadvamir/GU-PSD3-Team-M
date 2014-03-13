package uk.ac.glasgow.dcs_booking.components.login.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import uk.ac.glasgow.dcs_booking.components.login.Login;

public class Activator implements BundleActivator{
	
	private Authenticator authenticator;
    private ServiceRegistration<Login> authenticatorRegistration;
	
	@Override
	public void start(BundleContext context)
		throws Exception {
		
		authenticator = new Authenticator();
		
		authenticatorRegistration = context.registerService(Login.class, authenticator, null);
		
		System.out.println("Login service has started");
	}	

	@Override
	public void stop(BundleContext context)
		throws Exception {
		
		authenticatorRegistration.unregister();
		
		System.out.println("Login service has stopped");
	}
	

}
