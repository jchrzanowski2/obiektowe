package pl.edu.agh.to2.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.to2.example.dao.BookDetailsDAO;
import pl.edu.agh.to2.example.model.BookDetails;
import reactor.core.publisher.Flux;

@Service
public class BooksPageService {
    @Autowired
    private BookDetailsDAO bookDAO;

    public Flux<BookDetails> getAllBooks() {
        return bookDAO.findAll();
    }
}
