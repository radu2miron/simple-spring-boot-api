package edu.tucn.li;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    //todo: Access the swagger ui at: http://localhost:8081/swagger-ui/index.html
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}