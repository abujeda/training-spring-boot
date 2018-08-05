package com.adaybujeda.spring.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adaybujeda.spring.data.Repo;

@RestController
public class TopicResource {
    
    @Autowired
    private Repo repo;
    
    
    @RequestMapping("/topics")
    public String getTopics() {
        return "topics";
    }
    
    @RequestMapping("/topics/{topicCode}")
    public String getTopic(@PathVariable String topicCode) {
        repo.getTopic(topicCode);
        return "ID";
    }

}
