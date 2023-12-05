package pl.edu.agh.to2.example.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.to2.example.model.LoginUser;
import reactor.core.publisher.Mono;

@Repository
public interface UserDAO  extends CrudRepository<LoginUser, Integer> {
    Mono<LoginUser> findByEmail(String email);
}
