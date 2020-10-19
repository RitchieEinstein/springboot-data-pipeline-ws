package com.ritchieeinstein.falcon.websockstreamer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.SocketUtils;

@Configuration
public class CustomPortConfiguration implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    @Value("${port.number.min:1}")
    private Integer minPort;

    @Value("${port.number.max:65000}")
    private Integer maxPort;
    
    @Value("${server.port}")
    private String initialServerPort;

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        int port = SocketUtils.findAvailableTcpPort(minPort, maxPort);
        
        if(initialServerPort.equals("0")) {
//            log.info("Port is 0, let's assign a dynamic port..");
            factory.setPort(port);
            System.getProperties().put("server.port", port);
        }
    }
} 