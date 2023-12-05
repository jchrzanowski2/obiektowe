package pl.edu.agh.to2.example.dao.dto;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
@EnableR2dbcRepositories
public class Connection extends AbstractR2dbcConfiguration {
    @Override
    @Bean
    public ConnectionFactory connectionFactory() {
        ConnectionFactoryOptions options = ConnectionFactoryOptions.builder()
                .option(DRIVER, "mysql")
                .option(HOST, "mysql.agh.edu.pl")
                .option(USER, "olsza")
                .option(PORT, 3306)  // optional, default 3306
                .option(PASSWORD, "Y7n8KuS8nsbMdjCh") // optional, default null, null means has no password
                .option(DATABASE, "olsza") // optional, default null, null means not specifying the database
                .build();
        ConnectionFactory connectionFactory = ConnectionFactories.get(options);
        connectionFactory.create();
        return connectionFactory;
    }
}
