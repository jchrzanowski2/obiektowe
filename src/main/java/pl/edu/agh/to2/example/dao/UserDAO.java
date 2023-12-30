package pl.edu.agh.to2.example.dao;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.to2.example.model.LoginUser;
import reactor.core.publisher.Mono;

@Repository
public interface UserDAO extends R2dbcRepository<LoginUser, Long> {
    Mono<LoginUser> findByEmail(String email);

    @Query("""
            SELECT * from login_user l
            where l.email = :email and l.password = :password
            """)
    Mono<LoginUser> findAllByEmailAndPassword(String email, String password);

    @Query("""
            UPDATE login_user
            SET first_name = :newValue
            WHERE id = :id
            """)
    Mono<LoginUser> updateFirstName(Long id, String newValue);

    @Query("""
            UPDATE login_user
            SET last_name = :newValue
            WHERE id = :id
            """)
    Mono<LoginUser> updateLastName(Long id, String newValue);

    @Query("""
            UPDATE login_user
            SET password = :newValue
            WHERE id = :id
            """)
    Mono<LoginUser> updatePassword(Long id, String newValue);
}