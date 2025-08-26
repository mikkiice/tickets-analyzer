package com.example.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    private static final Properties props = new Properties();

    static {
        try (InputStream in = AppConfig.class.getClassLoader()
                .getResourceAsStream("application.properties")) {
            if (in != null) {
                props.load(in);
            } else {
                throw new RuntimeException("application.properties not found in resources!");
            }
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить application.properties", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
