package pl.edu.agh.to2.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.to2.example.dao.BookDetailsDAO;
import pl.edu.agh.to2.example.dao.dto.BooksFIlterDTO;
import pl.edu.agh.to2.example.model.BookDetails;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BooksPageService {
    @Autowired
    private BookDetailsDAO bookDAO;

    public Flux<BookDetails> getAllBooks() {
        return bookDAO.findAll();
    }

    public Flux<BookDetails> getMoviesWithFilterDTO(BooksFIlterDTO booksFilterDTO, int page, int maxItemsPerPage) {
        return bookDAO.findAllWithFilters(booksFilterDTO, page, maxItemsPerPage);
    }

    public Mono<Integer> getMovieCountWithFilter(BooksFIlterDTO booksFilterDTO) {
        return bookDAO.getCountWithFilters(booksFilterDTO);
    }
}
