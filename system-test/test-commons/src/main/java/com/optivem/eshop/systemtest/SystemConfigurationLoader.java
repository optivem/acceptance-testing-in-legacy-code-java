package com.optivem.eshop.systemtest;

import com.optivem.eshop.systemtest.core.ExternalSystemMode;
import com.optivem.eshop.systemtest.core.SystemConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class SystemConfigurationLoader {
    
    public static SystemConfiguration load(ExternalSystemMode mode) {
        String configFile = getConfigFileName(mode);
        Map<String, Object> config = loadYamlFile(configFile);

        var shopUiBaseUrl = getNestedStringValue(config, "test", "eshop", "ui", "baseUrl");
        var shopApiBaseUrl = getNestedStringValue(config, "test", "eshop", "api", "baseUrl");
        var erpBaseUrl = getNestedStringValue(config, "test", "erp", "api", "baseUrl");
        var taxBaseUrl = getNestedStringValue(config, "test", "tax", "api", "baseUrl");

        return new SystemConfiguration(shopUiBaseUrl, shopApiBaseUrl, erpBaseUrl, taxBaseUrl);
    }

    private static String getConfigFileName(ExternalSystemMode mode) {
        return switch (mode) {
            case REAL -> "application-real.yml";
            case STUB -> "application-stub.yml";
        };
    }

    private static Map<String, Object> loadYamlFile(String fileName) {
        var yaml = new Yaml();
        InputStream inputStream = SystemConfigurationLoader.class
                .getClassLoader()
                .getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalStateException("Configuration file not found: " + fileName);
        }

        return yaml.load(inputStream);
    }
    
    @SuppressWarnings("unchecked")
    private static <T> T getNestedValue(Map<String, Object> config, String... keys) {
        var current = config;
        for (int i = 0; i < keys.length - 1; i++) {
            current = (Map<String, Object>) current.get(keys[i]);
        }
        return (T) current.get(keys[keys.length - 1]);
    }

    private static String getNestedStringValue(Map<String, Object> config, String... keys) {
        return getNestedValue(config, keys);
    }
}
