package com.examportal.examportalbackend.core.utils;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageSBUtil {

    private final MessageSource messageSource;

    MessageSBUtil(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    public String getMessage(String message){
        return this.messageSource.getMessage(message,null, Locale.ENGLISH);
    }
}