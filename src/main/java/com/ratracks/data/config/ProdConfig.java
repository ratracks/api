package com.ratracks.data.config;

import java.util.logging.Logger;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdConfig {
    private static final Logger logger = Logger.getLogger(ProdConfig.class.getName());
  
    public void dbInstance(){
        logger.info("Should instance Prod DB");
    }
}
