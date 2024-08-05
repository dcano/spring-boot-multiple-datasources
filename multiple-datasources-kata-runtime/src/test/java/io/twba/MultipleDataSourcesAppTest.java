package io.twba;

import io.twba.bcb.EntityBcb;
import io.twba.bcb.EntityBcbRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.test.StepVerifier;

import java.util.UUID;

@SpringBootTest
@Testcontainers
@ContextConfiguration(classes = {MultipleDataSourcesApp.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles({"postgres", "reactive"})
public class MultipleDataSourcesAppTest {

    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:latest")
            .withDatabaseName("test_db")
            .withUsername("sa")
            .withPassword("sa");


    @DynamicPropertySource
    public static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.driver-class-name", container::getDriverClassName);
        registry.add("spring.r2dbc.username", container::getUsername);
        registry.add("spring.r2dbc.password", container::getPassword);
        registry.add("spring.r2dbc.properties.host", container::getHost);
        registry.add("spring.r2dbc.properties.port", container::getFirstMappedPort);
        registry.add("spring.r2dbc.properties.db-name", container::getDatabaseName);
    }

    @Autowired
    public EntityBcbRepository entityBcbRepository;

    @Test
    public void shouldStartApp() {
        //simple test to verify app starts
    }

    @Test
    public void shouldStoreDataInReactiveRepository() {
        EntityBcb entityBcb = new EntityBcb(UUID.randomUUID(), RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10));
        StepVerifier.create(entityBcbRepository.save(entityBcb))
                .expectNextMatches(e -> e.getId().equals(entityBcb.getId()))
                .expectComplete()
                .verify();
    }

}
