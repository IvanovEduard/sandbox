package spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:resource.properties")
public class SpringTest {

    public static void main(String[] args) {
        SpringApplication.run(SpringTest.class, args);
    }
}
