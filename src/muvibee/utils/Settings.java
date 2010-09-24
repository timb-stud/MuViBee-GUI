package muvibee.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Verwaltet die Settings in der muvibee.settings Datei
 * @author Tim Bartsch
 */
public class Settings{
    private Properties properties = new Properties();
    private static File file = new File("muvibee.settings");
    private static String languageKey = "language";
    private static String proxyHostKey = "proxyHost";
    private static String proxyPortKey = "proxyPort";

    public void load() throws IOException{
        if(!file.createNewFile()){
            FileInputStream in = new FileInputStream(file);
            properties.load(in);
        }
    }

    public void save() throws IOException{
        if(!file.createNewFile()){
            FileOutputStream out = new FileOutputStream(file);
            properties.store(out, "Kommentaraar");
        }
    }

    public String getLanguage() {
        return properties.getProperty(languageKey, "en");
    }

    public void setLanguage(String language) {
        properties.setProperty(languageKey, language);
    }

    public String getProxyPort() {
        return properties.getProperty(proxyPortKey, null);
    }

    public void setProxyPort(String proxyPort) {
        properties.setProperty(proxyPortKey, proxyPort);
    }

    public String getProxyHost() {
        return properties.getProperty(proxyHostKey, null);
    }

    public void setProxyHost(String proxyHost) {
        properties.setProperty(proxyHostKey, proxyHost);
    }

    
}
