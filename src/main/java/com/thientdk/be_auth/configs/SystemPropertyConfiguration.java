package com.thientdk.be_auth.configs;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@ConfigurationProperties("system.configurations")
public final class SystemPropertyConfiguration {

    private final String[] urlWhiteList;
    private final CorsProperty cors;

    public SystemPropertyConfiguration(String[] urlWhiteList, CorsProperty cors) {
        this.urlWhiteList = urlWhiteList;
        this.cors = cors;
    }

    public record CorsProperty(String allowedOriginPattern, String allowedHeader, List<String> allowedMethods,
                               boolean allowCredentials, String exposedHeader,
                               String urlBasedPatternCorsConfigurationSource) {
    }

}