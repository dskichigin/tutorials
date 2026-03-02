package dsk.tutorials.spring_examples_ioc.config_javabased;

import dsk.tutorials.spring.AppConfiguration;
import dsk.tutorials.spring.services.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainJavaBean {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfiguration.class);
        context.refresh();

        UserService userService = context.getBean(UserService.class);
        userService.start();
    }
}
