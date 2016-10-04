package org.jahia.services.render.scripting.quercus;

import com.caucho.quercus.script.QuercusScriptEngineFactory;
import com.caucho.vfs.ClasspathPath;
import com.caucho.vfs.Path;
import org.jahia.services.render.scripting.bundle.Configurable;
import org.osgi.framework.Bundle;

import javax.script.ScriptEngine;

/**
 * Created by loom on 20.05.15.
 */
public class JahiaQuercusScriptEngineFactory extends QuercusScriptEngineFactory implements Configurable {

    private Path iniFile = null;

    @Override
    public ScriptEngine getScriptEngine() {
        return new JahiaQuercusScriptEngine(this, iniFile);
    }

    @Override
    public void configurePreRegistration(Bundle bundle) {
    }

    @Override
    public void destroy(Bundle bundle) {
    }

    @Override
    public void configurePreScriptEngineCreation() {
        final String iniFileClassPathLocation = JahiaQuercusConfiguration.getInstance().getIniFileClassPathLocation();
        if (iniFile == null && iniFileClassPathLocation != null) {
            iniFile = new ClasspathPath(null, iniFileClassPathLocation, iniFileClassPathLocation);
        }
    }
}
