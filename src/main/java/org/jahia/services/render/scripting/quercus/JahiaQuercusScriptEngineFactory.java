package org.jahia.services.render.scripting.quercus;

import com.caucho.quercus.script.QuercusScriptEngineFactory;
import com.caucho.vfs.ClasspathPath;
import com.caucho.vfs.Path;
import org.springframework.beans.factory.InitializingBean;

import javax.script.ScriptEngine;

/**
 * Created by loom on 20.05.15.
 */
public class JahiaQuercusScriptEngineFactory extends QuercusScriptEngineFactory implements InitializingBean {

    private Path iniFile = null;
    private String iniFileClassPathLocation = "META-INF/quercus/php.ini";

    public void setIniFileClassPathLocation(String iniFileClassPathLocation) {
        this.iniFileClassPathLocation = iniFileClassPathLocation;
    }

    @Override
    public ScriptEngine getScriptEngine() {
        return new JahiaQuercusScriptEngine(this, iniFile);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (iniFileClassPathLocation != null) {
            iniFile = new ClasspathPath(null, iniFileClassPathLocation, iniFileClassPathLocation);
        }
    }
}
