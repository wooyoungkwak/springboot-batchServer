package com.example.springbootbatchserver.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Date : 2022-06-17
 * Author : zilet
 * Project : springboot-batchServer
 * Description :
 */
public class UtilConfiguration {

    @Bean
    public ObjectMapper objectMapper(){

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(dateFormat);
        objectMapper.registerModule(new JavaTimeModule());  // LocalDateTime 변환을위한 모듈 등록 (필수)
        return objectMapper;
    }
}
