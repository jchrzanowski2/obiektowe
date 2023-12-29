package pl.edu.agh.to2.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to2.example.event.LoginEvent;
import pl.edu.agh.to2.example.model.LoginUser;
import pl.edu.agh.to2.example.service.BorrowBookService;

import java.net.URL;
import java.util.stream.Collectors;

@Controller
public class BorrowBookController implements ApplicationListener<LoginEvent> {
    public Label bookListError;
    private LoginUser user;
    public VBox bookList;
    @Autowired
    BorrowBookService borrowBookService;

    @Autowired
    private BorrowBookService booksPageService;

    public static URL getFXML() {
        return BorrowBookController.class.getClassLoader().getResource("fxml/BorrowBook.fxml");
    }

    @FXML
    void initialize() {
        borrowBookService.getBooks().toStream().forEach(bookDetails -> {
            bookList.getChildren().add(new CheckBox(bookDetails.getFullTitle()));
        });
    }

    public void submit(ActionEvent event) {
        if (bookList.getChildren().stream().map(x -> (CheckBox) x).noneMatch(CheckBox::isSelected)) {
            bookListError.setText("At least one book must be selected.");
            bookListError.setVisible(true);
            return;
        }
        bookListError.setVisible(false);
        borrowBookService.submitBookReservation(
                user,
                bookList.getChildren().stream()
                        .map(x -> (CheckBox) x)
                        .filter(CheckBox::isSelected)
                        .map(CheckBox::getText)
                        .map(x -> x.split(":")[0])
                        .map(Long::parseLong)
                        .collect(Collectors.toList())
        );
        bookList.getChildren().stream()
                .map(x -> (CheckBox) x)
                .forEach(checkBox -> checkBox.setSelected(false));
        bookListError.setVisible(false);
    }

    @Override
    public void onApplicationEvent(LoginEvent event) {
        user = (LoginUser) event.getSource();
    }
}
