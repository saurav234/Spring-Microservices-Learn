package com.rest.webservices.springwebservices.controllers;

import com.rest.webservices.springwebservices.vo.HelloWorldBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HelloworldController {

    @Autowired
    MessageSource messageSource;


    //GET
    //URI - /hello-world
    //method - "helloWorld"
    @GetMapping(path="/hello-world/{name}")
    public HelloWorldBean helloWorld(@PathVariable String name) {
        return new HelloWorldBean("Hello Bro " + name);
    }

    @GetMapping(path="/hello-world-international/{name}")
    public HelloWorldBean helloWorldInternational(/*@RequestHeader(name="Accept-Language", required = false) Locale locale, */@PathVariable String name) {
        return new HelloWorldBean(messageSource.getMessage("good.morning.message", null, "Default Message" , LocaleContextHolder.getLocale()));
    }
}
