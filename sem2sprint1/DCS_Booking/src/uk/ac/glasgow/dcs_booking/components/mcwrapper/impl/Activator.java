package uk.ac.glasgow.dcs_booking.components.mcwrapper.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import uk.ac.glasgow.dcs_booking.components.mcwrapper.MyCampusWrapper;

public class Activator implements BundleActivator {
    private MyCampusController wrapper;

    private ServiceRegistration<MyCampusWrapper> wrapperRegistration;
    
    @Override
    public void start(BundleContext context) throws Exception {
        wrapper = new MyCampusController();

        wrapperRegistration = context.registerService(
            MyCampusWrapper.class, wrapper, null
        );

        wrapper.authorise();
        
        System.out.println("MyCampus wrapper service has started");
    }

    @Override
    public void stop(BundleContext arg0) throws Exception {
        wrapper.disconnect();
        wrapperRegistration.unregister();
        
        System.out.println("MyCampus wrapper service has stopped");
    }
}
