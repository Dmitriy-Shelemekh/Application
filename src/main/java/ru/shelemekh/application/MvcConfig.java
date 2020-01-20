package ru.shelemekh.application;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MvcConfig  implements WebMvcConfigurer {
    public void addViewController(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("MainPage");
//        registry.addViewController("/app").setViewName("AppPage");
//        registry.addViewController("/login").setViewName("LoginPage");
    }
}
