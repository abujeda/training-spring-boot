package com.adaybujeda.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.adaybujeda.spring.model.AppProperties;

@SpringBootApplication
public class HelloWorldApp {
    
    public static final void main(String... args) {
        SpringApplication.run(HelloWorldApp.class, args);
    }
    
    
    @Bean
    public TopicService topicService(AppProperties props) {
        System.out.println(props.getName());
        System.out.println(props.getPort());
        System.out.println(props.getMyTopic());
        return new TopicService();
    }
    
    @Bean
    public CommandLineRunner setup() {
        return (String ... args) -> {
            
        };
    }

}
