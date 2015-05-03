package org.jahia.services.render.scripting.quercus;

import org.jahia.utils.ScriptEngineUtils;
import org.springframework.beans.factory.InitializingBean;

import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import java.lang.reflect.Field;

/**
 * This class is mostly a hack to register the Quercus engine into the global ScriptEngineManager. Also it doesn't support
 * unregistration since the ScriptEngineManager doesn't support it.
 *
 * Possibly a better solution would be to implement inside Jahia's core the following use of the OsgiScriptEngineManager
 * as illustrated here :
 * http://svn.apache.org/repos/asf/felix/trunk/mishell/src/main/java/org/apache/felix/mishell/OSGiScriptEngineManager.java
 * but this requires modifying the ScriptEngineUtils class inside Jahia.
 */
public class ScriptEngineFactoryInitializer implements InitializingBean {

    ScriptEngineFactory scriptEngineFactory;

    public void setScriptEngineFactory(ScriptEngineFactory scriptEngineFactory) {
        this.scriptEngineFactory = scriptEngineFactory;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ScriptEngineUtils scriptEngineUtils = ScriptEngineUtils.getInstance();
        Field scriptEngineManagerField = scriptEngineUtils.getClass().getDeclaredField("scriptEngineManager");
        scriptEngineManagerField.setAccessible(true);
        ScriptEngineManager scriptEngineManager = (ScriptEngineManager) scriptEngineManagerField.get(scriptEngineUtils);
        for (String extension : scriptEngineFactory.getExtensions()) {
            scriptEngineManager.registerEngineExtension(extension, scriptEngineFactory);
        }
        for (String mimeType : scriptEngineFactory.getMimeTypes()) {
            scriptEngineManager.registerEngineMimeType(mimeType, scriptEngineFactory);
        }
        for (String name : scriptEngineFactory.getNames()) {
            scriptEngineManager.registerEngineName(name, scriptEngineFactory);
        }
    }
}
