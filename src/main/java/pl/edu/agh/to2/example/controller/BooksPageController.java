package pl.edu.agh.to2.example.controller;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to2.example.model.Book;
import pl.edu.agh.to2.example.model.BookDetails;
import pl.edu.agh.to2.example.service.BooksPageService;
import pl.edu.agh.to2.example.service.DeleteBookService;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BooksPageController {
    @FXML
    private ListView<BookDetails> booksListView;
    @FXML
    private Pagination pagination;
    @Autowired
    private BooksPageService booksPageService;
    @Autowired
    private DeleteBookService deleteBookService;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;
    @FXML
    private Button detailsButton;
    @Autowired
    private EditBookController editBookController;
    @Autowired
    private DetailBookController detailBookController;

    public static URL getFXML() {
        return AddBookController.class.getClassLoader().getResource("fxml/Books.fxml");
    }

    @FXML
    void initialize() {
        this.booksListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(BookDetails book, boolean empty) {
                super.updateItem(book, empty);
                if (empty || book == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(book.getTitle());
                    deleteButton.disableProperty().bind(
                            Bindings.isEmpty(booksListView.getSelectionModel()
                                    .getSelectedItems()));
                    editButton.disableProperty().bind(
                            Bindings.isEmpty(booksListView.getSelectionModel()
                                    .getSelectedItems()));
                    detailsButton.disableProperty().bind(
                            Bindings.isEmpty(booksListView.getSelectionModel()
                                    .getSelectedItems()));
                    Platform.runLater(() -> {
                        ImageView imageView = new ImageView();
                        var image2 = new Image(book.getCover(), 100, 0, true, true);
                        imageView.setImage(image2);
                        setGraphic(imageView);
                        setPrefHeight(100);
                    });
                }
            }
        });

        this.pagination.setMaxPageIndicatorCount(10);
        this.pagination.setPageFactory((pageIndex) -> {
            refreshList();
            return new VBox();
        });

        refreshList();
    }

    private Void refreshList() {
        //this.recommendedMovies = recommendationService.getRecommendedMovies().collectList().block();
        //Optional<Boolean> isRecommendedFilter = this.isRecommended.map(CheckBox::isSelected);
        //Optional<String> newNameFilter = Optional.ofNullable(name.getText()).filter(s -> !s.isEmpty());
        //Optional<Integer> newMinDuration = textFieldToOptInt(this.minDuration);
        //Optional<Integer> newMaxDuration = textFieldToOptInt(this.maxDuration);

        //var moviesFilterDTO = new MovieFiltersDTO(newMinDuration, newMaxDuration, newNameFilter, isRecommendedFilter);

        Platform.runLater(() -> {
            var numberPages = 1;
            pagination.setPageCount(numberPages);
            this.booksListView.setItems(FXCollections.observableArrayList(
                    booksPageService.getAllBooks().toStream().collect(Collectors.toList())));
        });
        return null;
    }

    @FXML
    public void handleDeleteAction(ActionEvent actionEvent) {
        var bookToRemove = booksListView.getSelectionModel()
                .getSelectedItem();
        deleteBookService.deleteBook(bookToRemove)
                .doOnSuccess(success -> {
                    Platform.runLater(this::refreshList);
                })
                .doOnError(Throwable::printStackTrace)
                .subscribe();
    }

    @FXML
    public void handleEditAction(ActionEvent actionEvent) {
        var bookToEdit = booksListView.getSelectionModel()
                .getSelectedItem();
        if (bookToEdit != null){
            editBookController.showBookEditDialog(bookToEdit);
        }
    }

    @FXML
    public void handleDetailsAction(ActionEvent actionEvent) {
        var bookToDetail = booksListView.getSelectionModel().getSelectedItem();
        if (bookToDetail != null){
            detailBookController.showBookEditDialog(bookToDetail);
        }
    }
}
