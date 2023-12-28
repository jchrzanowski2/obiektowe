package pl.edu.agh.to2.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.to2.example.dao.BookDAO;
import pl.edu.agh.to2.example.dao.BookDetailsDAO;
import pl.edu.agh.to2.example.model.Book;
import pl.edu.agh.to2.example.model.BookDetails;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class DeleteBookService {

    @Autowired
    BookDAO bookDAO;

    @Autowired
    BookDetailsDAO bookDetailsDAO;

    public Mono<Void> deleteBook(BookDetails book){
        return bookDAO.deleteById(book.getId())
                .then(bookDetailsDAO.deleteById(book.getId()));
    }
}
