package pl.edu.agh.to2.example.dao;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.to2.example.model.LoginUser;
import reactor.core.publisher.Mono;

@Repository
public interface UserDAO extends R2dbcRepository<LoginUser, Long> {
    Mono<LoginUser> findByEmail(String email);

}