package com.ohmyapp.elasticsearch.dao.config;

import com.ohmyapp.elasticsearch.dao.service.RunnerMovie;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * Created by Emerald on 11/6/2016.
 * elasticsearch config
 */
@Configuration
public class ElasticConfig {
    @Value("${elasticsearch.connection}")
    private String connection;
    @Value("${elasticsearch.clusterName}")
    private String clusterName;
    @Value("${elasticsearch.type}")
    private String type;
    @Value("${elasticsearch.path.home}")
    private String path;


    @Bean
    public RunnerMovie runnerMovie() {
        return new RunnerMovie();
    }

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() throws UnknownHostException {
        return new ElasticsearchTemplate(getNodeClient());
    }

    private Client getNodeClient() throws UnknownHostException {
        if ("embedded".equals(type)) {
            Settings settings = Settings.builder().put("path.home", path).build();
            return NodeBuilder.nodeBuilder().settings(settings).clusterName(clusterName).local(true).node().client();
        } else {
            Settings settings = Settings.builder().put("cluster.name", clusterName).build();
            String[] connectInfo = connection.split(":");
            return TransportClient.builder().settings(settings).build()
                    .addTransportAddress(new InetSocketTransportAddress(
                            new InetSocketAddress(connectInfo[0], Integer.parseInt(connectInfo[1]))));
        }
    }
}
