package com.example.demo_st;
//класс отвечает за запуск веб приложения

//2 метода и 1 аннотация отвечают за то, чтобы приложение инициализировалось
//и оно запустилось
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class StudentManagerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(StudentManagerApplication.class, args);
    }

}
