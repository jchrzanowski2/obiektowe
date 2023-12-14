package pl.edu.agh.to2.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to2.example.model.Book;
import pl.edu.agh.to2.example.model.BookDetails;
import pl.edu.agh.to2.example.service.AddBookService;

import java.net.URL;

@Controller
public class AddBookController {
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
    private Label authorErrorLabel;
    @FXML
    private Label titleError;
    @FXML
    private Label coverError;
    @FXML
    private Label contentsErrorLabel;
    @FXML
    private Label genreErrorLabel;
    @FXML
    private Label quantityErrorLabel;
    @FXML
    private Label errorLabel;
    @Autowired
    AddBookService addBookService;
    public static URL getFXML() {
        return AddBookController.class.getClassLoader().getResource("fxml/AddBook.fxml");
    }

    public void addNewBook(ActionEvent actionEvent) {
        clearErrorLabels();

        boolean authorCorrect = addBookService.validateAuthor(author.getText());
        boolean titleCorrect = addBookService.validateTitle(title.getText());
        boolean coverCorrect = addBookService.validateCover(cover.getText());
        boolean contentCorrect = addBookService.validateContents(contents.getText());
        boolean genreCorrect = addBookService.validateGenre(genre.getText());
        boolean quantityCorrect = addBookService.validateQuantity(quantity.getText());

        if (!authorCorrect) {
            authorErrorLabel.setVisible(true);
        }
        if (!titleCorrect) {
            titleError.setVisible(true);
        }
        if (!coverCorrect) {
            coverError.setVisible(true);
        }
        if (!contentCorrect) {
            contentsErrorLabel.setVisible(true);
        }
        if (!genreCorrect) {
            genreErrorLabel.setVisible(true);
        }
        if (!quantityCorrect) {
            quantityErrorLabel.setVisible(true);
        }

        if(authorCorrect && titleCorrect && coverCorrect && contentCorrect && genreCorrect && quantityCorrect){
            if (addBookService.isBookNotExisting(author.getText(), title.getText())){
                BookDetails bookDetails = new BookDetails(author.getText(), title.getText(), cover.getText(), contents.getText(),genre.getText());
                addBookService.addBookDetails(bookDetails).subscribe();
                Long bookDetailId = addBookService.getIDByAuthorAndTitle(author.getText(), title.getText());
                Book book = new Book(bookDetailId, Integer.parseInt(quantity.getText()), 5);
                addBookService.addBook(book).subscribe();
                errorLabel.setVisible(false);
            }
            else{
                errorLabel.setVisible(true);
            }
        }
        else{
            errorLabel.setVisible(true);
        }
    }

    private void clearErrorLabels() {
        authorErrorLabel.setVisible(false);
        titleError.setVisible(false);
        coverError.setVisible(false);
        contentsErrorLabel.setVisible(false);
        genreErrorLabel.setVisible(false);
        quantityErrorLabel.setVisible(false);
    }
}
