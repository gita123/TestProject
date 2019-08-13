package uk.gov.ho.cts.utils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    private static Properties configuration;
    private static Properties vaultConfig;

    static {
        initConfig();
    }

    public static String get(String string) {
        return configuration.getProperty(string);
    }

    public static String readCredentials(String propertyFile, String property) {
        Properties properties = new Properties();
        loadProperties(properties, getClassPathResourceStream(propertyFile));
        vaultConfig = properties;
        return vaultConfig.getProperty(property);
    }

    public static void initConfig() {
        String chartName = System.getenv("CHART_NAME") == null ? "default" : System.getenv("CHART_NAME");
        String propertyFile = "properties/" + chartName + ".properties";
        Properties localProperties = new Properties();
        loadProperties(localProperties, getClassPathResourceStream(propertyFile));
        configuration = localProperties;
    }

    private static void loadProperties(Properties props, InputStream inputStream) {
        try {
            props.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Could not load properties", e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    public static InputStream getClassPathResourceStream(String classpathResourceLoc) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(classpathResourceLoc);
    }

}
