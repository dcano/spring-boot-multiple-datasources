package io.twba.bca.config;

import io.twba.bca.EntityBcaRepository;
import io.twba.bca.EntityBcaRepositoryJpa;
import io.twba.bca.db.EntityBcaRepositoryJpaHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {
        "io.twba.bca"
})
@EntityScan(basePackages = {
        "io.twba.bca"
})
@EnableJpaRepositories(basePackages = {
        "io.twba.bca"
})
public class PersistenceConfig {

    @Bean
    public EntityBcaRepository entityBcaRepository(@Autowired EntityBcaRepositoryJpaHelper helper) {
        return new EntityBcaRepositoryJpa(helper);
    }

}
