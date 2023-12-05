package pl.edu.agh.to2.example.dao.dto;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.postgresql.client.SSLMode;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories
public class Connection extends AbstractR2dbcConfiguration {
    @Override
    @Bean
    public ConnectionFactory connectionFactory() {
        return new PostgresqlConnectionFactory(
                PostgresqlConnectionConfiguration
                        .builder()
                        .host("ep-raspy-shape-39138622.eu-central-1.aws.neon.tech")
                        .port(5432)
                        .database("Balwanki")
                        .username("bartek.kozera00")
                        .password("oXq3OgJYTH7j")
                        .sslMode(SSLMode.REQUIRE)
                        .build());
    }
}
