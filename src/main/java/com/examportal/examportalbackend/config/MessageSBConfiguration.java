package com.examportal.examportalbackend.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;


@Configuration
public class MessageSBConfiguration {

    @Bean
    public static MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/static/messages_en");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
