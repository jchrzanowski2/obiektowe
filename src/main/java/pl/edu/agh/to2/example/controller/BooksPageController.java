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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to2.example.dao.dto.BooksFIlterDTO;
import pl.edu.agh.to2.example.event.LoginEvent;
import pl.edu.agh.to2.example.model.BookDetails;
import pl.edu.agh.to2.example.model.LoginUser;
import pl.edu.agh.to2.example.service.BooksPageService;
import pl.edu.agh.to2.example.service.DeleteBookService;
import pl.edu.agh.to2.example.service.PermissionService;

import java.net.URL;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class BooksPageController implements ApplicationListener<LoginEvent> {
    @FXML
    private HBox hBoxFilters;
    @FXML
    private TextField title;
    @FXML
    private TextField author;
    @FXML
    private TextField genre;
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

    @Autowired
    private PermissionService permissionService;
    private LoginUser loggedInUser;

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

        if (!permissionService.canDeleteBooks(loggedInUser)) {
            deleteButton.setVisible(false);
        }
        if (!permissionService.canEditBooks(loggedInUser)) {
            editButton.setVisible(false);
        }
        if (!permissionService.canViewBooks(loggedInUser)) {
            detailsButton.setVisible(false);
        }

        this.title.setOnAction((value) -> refreshList());
        this.author.setOnAction((value) -> refreshList());
        this.genre.setOnAction((value) -> refreshList());

        this.pagination.setMaxPageIndicatorCount(10);
        this.pagination.setPageFactory((pageIndex) -> {
            refreshList();
            return new VBox();
        });
        refreshList();
    }

    private void refreshList() {
        Optional<String> newTitleFilter = Optional.ofNullable(title.getText()).filter(s -> !s.isEmpty());
        Optional<String> newAuthorFilter = Optional.ofNullable(author.getText()).filter(s -> !s.isEmpty());
        Optional<String> newGenreFilter = Optional.ofNullable(genre.getText()).filter(s -> !s.isEmpty());

        BooksFIlterDTO booksFIlterDTO = new BooksFIlterDTO(newTitleFilter, newAuthorFilter, newGenreFilter);

        Platform.runLater(() -> {
            var numberPages = Math.floorDiv(
                    booksPageService.getMovieCountWithFilter(booksFIlterDTO).block(),
                    pagination.getMaxPageIndicatorCount()
            );
            pagination.setPageCount(numberPages > 0 ? numberPages : 1);
            this.booksListView.setItems(FXCollections.observableArrayList(
                    booksPageService.getMoviesWithFilterDTO(
                                    booksFIlterDTO,
                                    pagination.getCurrentPageIndex(),
                                    pagination.getMaxPageIndicatorCount())
                            .toStream().collect(Collectors.toList())));
        });
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

    @Override
    public void onApplicationEvent(LoginEvent event) {
        this.loggedInUser = (LoginUser) event.getSource();
    }
}
