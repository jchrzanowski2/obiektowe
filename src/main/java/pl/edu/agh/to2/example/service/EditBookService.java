package pl.edu.agh.to2.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.to2.example.dao.BookDAO;
import pl.edu.agh.to2.example.dao.BookDetailsDAO;
import pl.edu.agh.to2.example.model.Book;
import pl.edu.agh.to2.example.model.BookDetails;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.regex.Pattern;

@Service
public class EditBookService {
    @Autowired
    BookDAO bookDAO;
    @Autowired
    BookDetailsDAO bookDetailsDAO;

    private final Pattern quantityPattern = Pattern.compile("^[1-9][0-9]*$");
    public boolean validateAuthor(String author){return !author.isEmpty();}
    public boolean validateTitle(String title){
        return !title.isEmpty();
    }
    public boolean validateCover(String cover){ return !cover.isEmpty();}
    public boolean validateContents(String contents){
        return !contents.isEmpty();
    }
    public boolean validateGenre(String genre){return !genre.isEmpty();}

    public boolean validateQuantity(String quantity){
        return !quantity.isEmpty()  && quantityPattern.matcher(quantity).matches();
    }

    public Mono<Book> updateBook(Book book, String quantity){
        return this.bookDAO.updateBook(book.getId(), Integer.parseInt(quantity));
    }

    public Mono<BookDetails> updateBookDetails(BookDetails bookDetails, String author, String title, String cover, String contents, String genre){
        return this.bookDetailsDAO.updateBookDetails(bookDetails.getId(), author, title, cover, contents, genre);
    }
}
