package pl.edu.agh.to2.example.dao;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import pl.edu.agh.to2.example.model.BookDetails;
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

}
