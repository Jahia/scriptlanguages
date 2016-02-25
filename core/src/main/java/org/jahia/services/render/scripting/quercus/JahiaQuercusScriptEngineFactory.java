package org.jahia.services.render.scripting.quercus;

import com.caucho.quercus.script.QuercusScriptEngineFactory;
import com.caucho.vfs.ClasspathPath;
import com.caucho.vfs.Path;

import javax.script.ScriptEngine;

/**
 * Created by loom on 20.05.15.
 */
public class JahiaQuercusScriptEngineFactory extends QuercusScriptEngineFactory {

    private Path iniFile = null;

    @Override
    public ScriptEngine getScriptEngine() {
        return new JahiaQuercusScriptEngine(this, iniFile);
    }

    void setIniFile(String iniFileClassPathLocation) {
        if (iniFile == null && iniFileClassPathLocation != null) {
            iniFile = new ClasspathPath(null, iniFileClassPathLocation, iniFileClassPathLocation);
        }
    }
}
