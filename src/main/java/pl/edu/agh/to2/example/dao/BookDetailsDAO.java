package pl.edu.agh.to2.example.dao;

import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import pl.edu.agh.to2.example.model.BookDetails;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookDetailsDAO  extends R2dbcRepository<BookDetails, Long> {
    @Query("""
            SELECT * from book_details bd
            where bd.author = :author and bd.title = :title
            """)
    Mono<BookDetails> findBookDetailsByAuthorAndAndTitle(String email, String title);

    @Query("""
            SELECT * from book_details
            """)
    Mono<BookDetails> findAllBooks();

    @Query("""
            SELECT bd.* from book_details bd
            JOIN book b ON b.id = bd.id
            WHERE b.quantity > 0
            """)
    Flux<BookDetails> findAvailable();

    @Modifying
    @Query("""
        DELETE FROM book_details
        WHERE id = :id
        """)
    Mono<Void> deleteById(Long id);

    @Query("""
        SELECT * FROM book_details
        WHERE id = :id
        """)
    Mono<BookDetails> findByID(Long id);

    @Modifying
    @Query("""
        UPDATE book_details
        SET author = :author, title = :title, cover = :cover, contents = :contents, genre = :genre
        WHERE id = :id
        """)
    Mono<BookDetails> updateBookDetails(Long id, String author, String title, String cover, String contents, String genre);

}
