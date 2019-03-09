package fr.amisss.core;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "fr.amisss.core")
@PropertySource({"classpath:META-INF/datasource.properties", "classpath:META-INF/core-config.properties"})
public class CoreConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties firstDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSource firstDataSource() {
        return firstDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    @ConfigurationProperties("camunda.datasource")
    public DataSourceProperties camundaProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "camundaBpmDataSource")
    @ConfigurationProperties("camunda.datasource")
    public DataSource camundaDataSource() {
        return camundaProperties().initializeDataSourceBuilder().build();
    }
}
