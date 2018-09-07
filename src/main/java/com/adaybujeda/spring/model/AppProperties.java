package com.adaybujeda.spring.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("topic")
@Component
public class AppProperties {
    private String name;
    private String status;
    private Integer port;
    private Topic myTopic = new Topic();
    
    public Integer getPort() {
        return port;
    }
    public void setPort(Integer port) {
        this.port = port;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Topic getMyTopic() {
        return myTopic;
    }
    public void setMyTopic(Topic myTopic) {
        this.myTopic = myTopic;
    }
}
