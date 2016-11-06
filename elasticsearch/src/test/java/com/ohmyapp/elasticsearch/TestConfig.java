package com.ohmyapp.elasticsearch;

import com.ohmyapp.elasticsearch.dao.service.RunnerMovie;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.util.Properties;

/**
 * Created by Emerald on 7/25/2016.
 *
 */
@Configuration
@ComponentScan
@EnableElasticsearchRepositories
public class TestConfig {
    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer()
    {
        PropertySourcesPlaceholderConfigurer placeholder = new PropertySourcesPlaceholderConfigurer();
        Properties property = new Properties();
        property.setProperty("elasticsearch.connection", "localhost:9300");
        property.setProperty("elasticsearch.clusterName", "es");
        property.setProperty("elasticsearch.type", "embedded");
        property.setProperty("elasticsearch.path.home", "build/temp");
        placeholder.setProperties(property);
        return placeholder;
    }

    @Bean
    public RunnerMovie runnerMovie() {
        return new RunnerMovie();
    }

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() {
        return new ElasticsearchTemplate(getNodeClient());
    }

    private Client getNodeClient() {
        Settings settings = Settings.builder().put("path.home", "build/data").build();
        return NodeBuilder.nodeBuilder().settings(settings).clusterName("es").local(true).node().client();
    }
}
