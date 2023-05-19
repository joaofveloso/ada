package br.com.ibis.glue;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@DirtiesContext
@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SpringTestContextLoader {

    @Container
    public final static MongoDBContainer mongoDbContainer =
            new MongoDBContainer("mongo:latest");

    @Container
    public final static GenericContainer smtpContainer = new GenericContainer("mailhog/mailhog")
            .withExposedPorts(1025, 8025);

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {

        smtpContainer.start();
        mongoDbContainer.start();
        
        registry.add("spring.data.mongodb.uri", () -> mongoDbContainer.getConnectionString() + "/mydatabase");
        registry.add("app.smtp.host", () -> smtpContainer.getHost());
        registry.add("app.smtp.port", () -> smtpContainer.getMappedPort(1025));
    }
}
