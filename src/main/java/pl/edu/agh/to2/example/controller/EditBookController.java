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

import java.net.URL;

@Controller
public class EditBookController {

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
    EditBookService editBookService;

    @Autowired
    DeleteBookService deleteBookService;

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
                            },
                            error -> {
                            }
                    );
        }
    }

    @FXML
    private void handleOkAction(ActionEvent event) {
        clearErrorLabels();

        boolean authorCorrect = editBookService.validateAuthor(author.getText());
        boolean titleCorrect = editBookService.validateTitle(title.getText());
        boolean coverCorrect = editBookService.validateCover(cover.getText());
        boolean contentCorrect = editBookService.validateContents(contents.getText());
        boolean genreCorrect = editBookService.validateGenre(genre.getText());
        boolean quantityCorrect = editBookService.validateQuantity(quantity.getText());

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
            /*bookDAO.findByID(bookDetails.getId())
                    .subscribe(
                            book -> {
                                editBookService.updateBook(book).subscribe();
                            },
                            error -> {
                            }
                    );
            editBookService.updateBookDetails(bookDetails).subscribe();*/
            deleteBookService.deleteBook(bookDetails).subscribe();
            if (editBookService.isBookNotExisting(author.getText(), title.getText())){
                BookDetails bookDetails = new BookDetails(author.getText(), title.getText(), cover.getText(), contents.getText(),genre.getText());
                editBookService.addBookDetails(bookDetails).subscribe();
                Long bookDetailId = editBookService.getIDByAuthorAndTitle(author.getText(), title.getText());
                Book book = new Book(bookDetailId, Integer.parseInt(quantity.getText()), 5);
                editBookService.addBook(book).subscribe();
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

    public static URL getFXML() {
        return EditBookController.class.getClassLoader().getResource("fxml/BookEditDialog.fxml");
    }

    public void showBookEditDialog(BookDetails bookDetails) {
        this.bookDetails = bookDetails;

        SceneChanger.setPane(getFXML());
        this.setData(bookDetails);

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