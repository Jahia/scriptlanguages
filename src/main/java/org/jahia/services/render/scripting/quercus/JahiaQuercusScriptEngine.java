package org.jahia.services.render.scripting.quercus;

import com.caucho.quercus.QuercusContext;
import com.caucho.quercus.script.QuercusScriptEngine;
import com.caucho.quercus.script.QuercusScriptEngineFactory;
import com.caucho.vfs.Path;

/**
 * Created by loom on 20.05.15.
 */
public class JahiaQuercusScriptEngine extends QuercusScriptEngine {

    private QuercusContext _quercus;
    private Path iniFile;

    public JahiaQuercusScriptEngine(QuercusScriptEngineFactory factory, Path iniFile) {
        super(factory);
        this.iniFile = iniFile;
    }

    private static QuercusContext createQuercus(String scriptEncoding,
                                                boolean isUnicodeSemantics, Path iniFile)
    {
        QuercusContext quercus = new QuercusContext();

        quercus.setScriptEncoding(scriptEncoding);
        quercus.setUnicodeSemantics(isUnicodeSemantics);

        quercus.setIniFile(iniFile);

        quercus.init();
        quercus.start();

        return quercus;
    }

    public Path getIniFile() {
        return iniFile;
    }

    public void setIniFile(Path iniFile) {
        this.iniFile = iniFile;
    }

    /**
     * Returns the Quercus object.
     * php/214h
     */
    public QuercusContext getQuercus()
    {
        if (_quercus == null) {
            _quercus = createQuercus(getScriptEncoding(), isUnicodeSemantics(), getIniFile());
        }

        return _quercus;
    }

    /**
     * Shuts down Quercus and free resources.
     */
    public void close()
    {
        if (_quercus != null) {
            _quercus.close();

            _quercus = null;
        }
    }

}
