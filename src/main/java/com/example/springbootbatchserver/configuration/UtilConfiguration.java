package com.example.springbootbatchserver.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;

/**
 * Date : 2022-06-17
 * Author : zilet
 * Project : springboot-batchServer
 * Description :
 */
public class UtilConfiguration {

    @Bean
    public ObjectMapper objectMapper(){

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper;
    }
}
