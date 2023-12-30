package pl.edu.agh.to2.example.dao;

import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.to2.example.model.Book;
import pl.edu.agh.to2.example.model.BookDetails;
import reactor.core.publisher.Mono;

@Repository
public interface BookDAO extends R2dbcRepository<Book, Long> {
    @Query("""
            INSERT INTO book(id, quantity, rating)
            values (:id, :quantity, :rating)
            """)
    Mono<Book> save(Long id, int quantity, int rating);

    @Modifying
    @Query("""
        DELETE FROM book
        WHERE id = :id
        """)
    Mono<Void> deleteById(Long id);

    @Query("""
        SELECT * FROM book
        WHERE id = :id
        """)
    Mono<Book> findByID(Long id);

    @Modifying
    @Query("""
        UPDATE book
        SET quantity = :quantity
        WHERE id = :id
        """)
    Mono<Book> updateBook(Long id, int quantity);
}
