package dsk.tutorials.spring;

import dsk.tutorials.spring.services.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    // https://github.com/dskichigin/tutorials.git

    public static void main(String[] args) {
        // Instantiate the ApplicationContext using a @Configuration class
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        UserService userService = context.getBean(UserService.class);
        userService.start();

    }
}