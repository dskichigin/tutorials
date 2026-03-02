package dsk.tutorials.spring_examples_ioc.config_javabased;

import dsk.tutorials.spring_examples_ioc.IUserDAO;
import dsk.tutorials.spring_examples_ioc.IUserService;
import dsk.tutorials.spring_examples_ioc.UserDAO;
import dsk.tutorials.spring_examples_ioc.UserServiceIoC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    IUserDAO userDAO(@Value("${db.url}") String url) {
        return new UserDAO(url);
    }

    @Bean
    IUserService userService(IUserDAO dao) {
        return new UserServiceIoC(dao);
    }
}
