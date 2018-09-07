package com.adaybujeda.spring.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern="yyyy-MM-dd")
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
    
    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("code", code).add("name", name).add("createdDate", createdDate).toString();
    }
}
