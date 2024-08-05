package io.twba.bcb.config;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import io.twba.bcb.EntityBcbRepository;
import io.twba.bcb.EntityBcbRepositoryPostgresReactive;
import io.twba.bcb.db.EntityBcbRepositoryReactiveHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableR2dbcRepositories(
        basePackages = "io.twba.bcb"
)
@EnableTransactionManagement
public class PersistenceBcbConfig {

    public static final String HOST = "host";
    public static final String PORT = "port";
    public static final String DB_NAME = "db-name";

    @Bean
    public ConnectionFactory connectionFactory(@Autowired R2dbcProperties r2dbcProperties) {

        return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration
                .builder()
                .password(r2dbcProperties.getPassword())
                .username(r2dbcProperties.getUsername())
                .host(r2dbcProperties.getProperties().get(HOST))
                .port(Integer.parseInt(r2dbcProperties.getProperties().get(PORT)))
                .database(r2dbcProperties.getProperties().get(DB_NAME))
                .build());
    }

    @ConfigurationProperties(prefix = "spring.r2dbc")
    @Bean
    public R2dbcProperties r2dbcDataSourceProperties() {
        return new R2dbcProperties();
    }

    @Bean
    public R2dbcEntityTemplate r2dbcEntityTemplate(ConnectionFactory connectionFactory) {
        return new R2dbcEntityTemplate(connectionFactory);
    }

    @Bean
    public ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }

    @Bean
    public EntityBcbRepository entityBcbRepository(@Autowired EntityBcbRepositoryReactiveHelper helper) {
        return new EntityBcbRepositoryPostgresReactive(helper);
    }

}
