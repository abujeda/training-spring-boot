package com.adaybujeda.spring.data;

import static com.datastax.driver.core.ConsistencyLevel.LOCAL_ONE;
import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.adaybujeda.spring.model.Topic;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SocketOptions;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.policies.RoundRobinPolicy;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;

@Repository
public class Repo {
    
    private static final Logger log = LoggerFactory.getLogger(Repo.class);
    
    private Cluster repoCluster;
    private Session session;
    
    
    public Repo() {
        Cluster.Builder builder = Cluster.builder();
        builder.addContactPoint("localhost").withLoadBalancingPolicy(new RoundRobinPolicy());
        
        SocketOptions socketOptions = new SocketOptions().setKeepAlive(false);
        socketOptions.setConnectTimeoutMillis(5000);
        socketOptions.setReadTimeoutMillis(10000);
        builder.withSocketOptions(socketOptions);
        
        this.repoCluster = builder.build();
        this.session = repoCluster.connect("spring");
        log.info("Started repo");
    }
    
    @PreDestroy
    public void shutdown() {
        log.info("Shutting down repo");
        repoCluster.close();
    }
    
    public Topic getTopic(String code) {
        Select select = QueryBuilder.select().from("topics");
        select.where(eq("topic_code", QueryBuilder.bindMarker()));
        select.setConsistencyLevel(LOCAL_ONE);
        Statement statement = session.prepare(select).bind(code);
        
        ResultSet execute = session.execute(statement);
        
        MappingManager manager = new MappingManager(session);
        Mapper<Topic> mapper = manager.mapper(Topic.class);
        Result<Topic> topics = mapper.map(execute);
        
        for (Topic t : topics) {
            System.out.println("Topic : " + t);
        }

        return null;
    }

}
