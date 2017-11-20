package com.epsi.music.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix="cors")
@Data
public class CorsAllowedHosts {

    private List<String> allowedHosts;
}
