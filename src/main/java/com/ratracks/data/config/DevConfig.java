package com.ratracks.data.config;

import java.util.logging.Logger;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {
    private static final Logger logger = Logger.getLogger(DevConfig.class.getName());

    public void dbInstance(){
        logger.info("Should instance Dev DB");
    }
}
