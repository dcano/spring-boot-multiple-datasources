package io.twba.bca.config;

import io.twba.bca.EntityBcaRepository;
import io.twba.bca.EntityBcaRepositoryJpa;
import io.twba.bca.db.EntityBcaRepositoryJpaHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@ComponentScan(basePackages = {
        "io.twba.bca"
})
@EntityScan(basePackages = {
        "io.twba.bca"
})
@EnableJpaRepositories(
        basePackages = {
                "io.twba.bca"
        },
        entityManagerFactoryRef = "jpaEntityManagerFactory",
        transactionManagerRef = "jpaTransactionManager")
@EnableTransactionManagement
public class PersistenceBcaConfig {

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    public DataSource jpaDataSource() {
        return jpaDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean jpaEntityManagerFactory(@Autowired DataSource dataSource, EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages("io.twba.bca")
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager todosTransactionManager(@Autowired LocalContainerEntityManagerFactoryBean jpaEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(jpaEntityManagerFactory.getObject()));
    }

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSourceProperties jpaDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public EntityBcaRepository entityBcaRepository(@Autowired EntityBcaRepositoryJpaHelper helper) {
        return new EntityBcaRepositoryJpa(helper);
    }

}
