package dsk.tutorials.spring_examples_ioc.config_xml;

import dsk.tutorials.spring_examples_ioc.IUserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainXML {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("app-config.xml");

        IUserService userService = context.getBean(IUserService.class);
    }
}
