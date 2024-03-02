package com.example.productsservice.configs;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration  // @Configuration annotation is used to mark the class as a configuration class.
                // This class will be used to configure the beans that we want to use in our application.
public class ApplicationConfiguration {

    @Bean // This annotation will create an object of RestTemplate and add it to the Spring container.
          // And this single object will be used across the application.
    public RestTemplate createRestTemplate(){
        return new RestTemplateBuilder().build();
    }
}
