package com.adaybujeda.spring.model;

import java.util.Date;

import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.google.common.base.MoreObjects;

@Table(name = "topics")
public class Topic {
    @PartitionKey
    @Column(name = "topic_code")
    private String code;
    @Column(name = "topic_name")
    private String name;
    @ClusteringColumn
    @Column(name = "creation_date")
    private Date createdDate;
    
    public Topic() {
        
    }
    
    public Topic(String code, String name, Date createdDate) {
        this.code = code;
        this.name = name;
        this.createdDate = createdDate;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Date getCreatedDate() {
        return createdDate;
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("code", code).add("name", name).add("createdDate", createdDate).toString();
    }

}
