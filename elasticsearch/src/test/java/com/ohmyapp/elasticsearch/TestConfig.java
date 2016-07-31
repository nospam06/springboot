package com.ohmyapp.elasticsearch;

import com.ohmyapp.elasticsearch.service.RunnerMovie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
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
        property.setProperty("elasticsearch.dataPath", "build/temp");
        placeholder.setProperties(property);
        return placeholder;
    }

    @Bean
    public RunnerMovie runnerMovie() {
        return new RunnerMovie();
    }
}
