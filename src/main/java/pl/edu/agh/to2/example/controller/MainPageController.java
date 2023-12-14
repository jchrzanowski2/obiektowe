package pl.edu.agh.to2.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to2.example.Enum.PageEnum;
import pl.edu.agh.to2.example.event.LoginEvent;
import pl.edu.agh.to2.example.service.PermissionService;
import pl.edu.agh.to2.example.util.SceneChanger;

import java.net.URL;

@Controller
public class MainPageController implements ApplicationListener<LoginEvent> {
    @FXML
    public ListView<String> categoriesListView;
    @FXML
    public BorderPane mainPane;

    @Autowired
    PermissionService permissionService;
    public static URL getFXML() {
        return MainPageController.class.getResource("/fxml/MainPage.fxml");
    }

    @FXML
    public void initialize() {
        SceneChanger.setMainPane(mainPane);
        categoriesListView.getItems().add("add_book");
        categoriesListView.getItems().add("books");
        categoriesListView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue != null) {
                PageEnum currentPage = PageEnum.valueOf(observable.getValue().toUpperCase());
                SceneChanger.setPane(currentPage.getUrl());
            }
        }));
    }

    public void logout(ActionEvent actionEvent) {
        SceneChanger.changeScene(WelcomePageController.getFXML());
    }

    @Override
    public void onApplicationEvent(LoginEvent event) {
        //TODO
    }
}
