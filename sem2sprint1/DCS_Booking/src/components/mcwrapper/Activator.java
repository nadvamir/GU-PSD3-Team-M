package components.mcwrapper;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
    private MyCampusController wrapper;

    private ServiceRegistration<MyCampusWrapper> 
        wrapperRegistration;
    
    @Override
    public void start(BundleContext context) throws Exception {
        wrapper = new MyCampusController();

        wrapperRegistration = context.registerService(
            MyCampusWrapper.class, wrapper, null
        );

        wrapper.authorise();
    }

    @Override
    public void stop(BundleContext arg0) throws Exception {
        monitor.stopMonitoring();
        wrapper.disconnect();
        wrapperRegistration.unregister();
    }
}
