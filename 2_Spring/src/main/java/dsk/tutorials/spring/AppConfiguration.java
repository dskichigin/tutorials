package dsk.tutorials.spring;

import dsk.tutorials.spring.factory.DataSourceFactory;
import dsk.tutorials.spring.factory.HikariDataSourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"dsk.tutorials.spring.*"})
@PropertySource(value = "file:${user.dir}/spring.properties", ignoreResourceNotFound = true)
public class AppConfiguration {
    @Value("${db.url:jdbc:postgresql://127.0.0.1/tutorials}")
    private String url;
    @Value("${db.user}")
    private String user;
    @Value("${db.password}")
    private String password;


    @Profile("!pool")
    @Bean
    DataSource dataSource() {
        DataSourceFactory connectionFactory
                = new DataSourceFactory(url, user, password);
        return connectionFactory.getDataSource();
    }
    @Profile("pool")
    @Bean
    DataSource dataSourcePool() {
        HikariDataSourceFactory connectionFactory
                = new HikariDataSourceFactory(url, user, password);
        return connectionFactory.getDataSource();
    }

}
