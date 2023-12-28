package pl.edu.agh.to2.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to2.example.dao.BookDAO;
import pl.edu.agh.to2.example.model.Book;
import pl.edu.agh.to2.example.model.BookDetails;
import pl.edu.agh.to2.example.service.DeleteBookService;
import pl.edu.agh.to2.example.service.EditBookService;
import pl.edu.agh.to2.example.util.SceneChanger;
import reactor.core.publisher.Mono;

import java.net.URL;

@Controller
public class DetailBookController {
    @FXML
    private TextField author;
    @FXML
    private TextField title;
    @FXML
    private TextField cover;
    @FXML
    private TextField contents;
    @FXML
    private TextField genre;
    @FXML
    private TextField quantity;
    @FXML
    private TextField rating;

    @Autowired
    BookDAO bookDAO;

    private BookDetails bookDetails;

    public void setData(BookDetails bookDetails) {
        this.bookDetails = bookDetails;
        if (bookDetails != null) {
            author.setText(bookDetails.getAuthor());
            title.setText(bookDetails.getTitle());
            cover.setText(bookDetails.getCover());
            contents.setText(bookDetails.getContents());
            genre.setText(bookDetails.getGenre());
            bookDAO.findByID(bookDetails.getId())
                    .subscribe(
                            book -> {
                                quantity.setText(String.valueOf(book.getQuantity()));
                                rating.setText(String.valueOf(book.getRating()));
                            },
                            error -> {
                            }
                    );
        }
    }

    public static URL getFXML() {
        return EditBookController.class.getClassLoader().getResource("fxml/DetailBook.fxml");
    }

    public void showBookEditDialog(BookDetails bookDetails) {
        this.bookDetails = bookDetails;

        SceneChanger.setPane(this.getFXML());
        this.setData(bookDetails);
    }
}
