package pl.edu.agh.to2.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.Scene;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import pl.edu.agh.to2.example.controller.RegisterPageController;
import pl.edu.agh.to2.example.service.RegisterService;
import pl.edu.agh.to2.example.util.SceneChanger;

import java.io.IOException;

public class App extends Application  {
    private ConfigurableApplicationContext applicationContext;
    @Override
    public void init() {
        applicationContext = new SpringApplicationBuilder(Main.class).run();
        SceneChanger.setApplicationContext(applicationContext);
    }

    @Override
    public void start(Stage stage) throws Exception {
        SceneChanger.setMainStage(stage);
        SceneChanger.changeScene(RegisterPageController.getFXML());
        stage.show();
        //try {
        //    // load layout from FXML file
        //    var loader = new FXMLLoader();
        //    loader.setLocation(RegisterPageController.class.getClassLoader().getResource("fxml/RegisterPanel.fxml"));
        //    VBox rootLayout = loader.load();
//
        //    // add layout to a scene and show them all
        //    configureStage(stage, rootLayout);
        //    stage.show();
//
        //} catch (IOException e) {
        //    // don't do this in common apps
        //    e.printStackTrace();
        //}
    }

   @Override
   public void stop() {
       applicationContext.close();
       Platform.exit();
   }
}
