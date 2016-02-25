package org.jahia.services.render.scripting.quercus;

import com.caucho.quercus.servlet.QuercusServlet;
import org.eclipse.gemini.blueprint.context.BundleContextAware;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.util.Dictionary;

/**
 * Service listener to make sure that we don't block startup
 */
public class HttpServiceListener implements BundleContextAware {

    public static final Logger logger = LoggerFactory.getLogger(HttpServiceListener.class);

    Servlet servlet;
    BundleContext bundleContext;
    String alias;
    Dictionary<String,String> initParameters;

    public void setBundleContext(BundleContext bundleContext) {
        this.bundleContext = bundleContext;
    }

    public void setServlet(Servlet servlet) {
        this.servlet = servlet;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setInitParameters(Dictionary<String, String> initParameters) {
        this.initParameters = initParameters;
    }

    public void onBind(ServiceReference serviceReference) {
        // note : we don't use the passed service reference because it is a proxy class that we cannot use to retrieve the
        // real service object, so we simply look it up again
        ServiceReference realServiceReference = bundleContext.getServiceReference(HttpService.class.getName());
        HttpService httpService = (HttpService) bundleContext.getService(realServiceReference);
        try {
            ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();
            Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
            QuercusServlet quercusServlet = (QuercusServlet) servlet;
            httpService.registerServlet(alias, servlet, initParameters, null);
            logger.info("Successfully registered custom servlet at /modules" + alias);
            Thread.currentThread().setContextClassLoader(oldClassLoader);
        } catch (ServletException | NamespaceException e) {
            e.printStackTrace();
        }

    }

    public void onUnbind(ServiceReference serviceReference) {
        // note : we don't use the passed service reference because it is a proxy class that we cannot use to retrieve the
        // real service object, so we simply look it up again
        // here we have a lot of null checks because in the case of a framework shutdown the service can disappear
        // at any time.
        if (serviceReference == null) {
            return;
        }
        ServiceReference realServiceReference = bundleContext.getServiceReference(HttpService.class.getName());
        if (realServiceReference == null) {
            return;
        }
        HttpService httpService = (HttpService) bundleContext.getService(realServiceReference);
        if (httpService == null) {
            return;
        }
        httpService.unregister(alias);
        logger.info("Successfully unregistered custom servlet from /modules" + alias);
    }
}