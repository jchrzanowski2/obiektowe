package pl.edu.agh.to2.example.dao;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.to2.example.model.ReservationBook;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ReservationBookDAO extends R2dbcRepository<ReservationBook, Long> {
    @Query("""
            INSERT INTO reservation_book(reservationid, bookid, quantity)
            values (:reservationId, :bookId, :quantity)
            """)
    Mono<ReservationBook> save(int reservationId, int bookId, int quantity);

    @Query("""
            SELECT * FROM reservation_book
            WHERE reservationId = :reservationId
            """)
    Flux<ReservationBook> findByReservationId(int reservationId);
}