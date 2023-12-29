package pl.edu.agh.to2.example.dao;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.to2.example.model.Book;
import pl.edu.agh.to2.example.model.Reservations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDate;

@Repository
public interface ReservationsDAO extends R2dbcRepository<Reservations, Long> {
    @Query("""
            INSERT INTO reservations(userid, startdate, enddate)
            values (:userId, :startDate, :endDate)
            """)
    Mono<Reservations> save(Long userId, LocalDate startDate, LocalDate endDate);

    @Query("""
            SELECT * FROM reservations
            WHERE userid = :userId
            """)
    Flux<Reservations> findByUserId(Long userId);
}