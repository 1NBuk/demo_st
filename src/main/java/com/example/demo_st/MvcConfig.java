package com.example.demo_st;

//класс для объединение всех методов в одно springboot приложение
//все зависимости будут взаимодейстовать во всех классах

//аннотации для объединение всех методов в одно springboot приложение
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    }

}