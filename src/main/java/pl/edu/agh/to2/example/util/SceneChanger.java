package pl.edu.agh.to2.example.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.springframework.context.ConfigurableApplicationContext;
import org.w3c.dom.Text;
import pl.edu.agh.to2.example.controller.RegisterPageController;
import java.io.IOException;
import java.net.URL;

public class SceneChanger {
    private static ConfigurableApplicationContext applicationContext;
    private static Stage mainStage;
    private static BorderPane mainPane;

    public static void changeScene(URL fxml) {
        var fxmlLoader = new FXMLLoader(fxml);
        //fxmlLoader.setControllerFactory(applicationContext::getBean);
        try {
            fxmlLoader.setLocation(fxml);
            mainStage.setScene(new Scene(fxmlLoader.load(), 800, 600));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mainStage.setTitle("Library");
    }

    public static void setPane(URL fxml) {
        var fxmlLoader = new FXMLLoader(fxml);
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        try {
            mainPane.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setApplicationContext(ConfigurableApplicationContext applicationContext) {
        SceneChanger.applicationContext = applicationContext;
    }

    public static void setMainStage(Stage mainStage) {
        SceneChanger.mainStage = mainStage;
    }

    public static void setMainPane(BorderPane mainPane) {
        SceneChanger.mainPane = mainPane;
    }
}