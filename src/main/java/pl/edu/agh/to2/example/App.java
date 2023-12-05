package pl.edu.agh.to2.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import pl.edu.agh.to2.example.controller.RegisterPageController;
import pl.edu.agh.to2.example.util.SceneChanger;

@SpringBootApplication
public class App extends Application  {
    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        applicationContext = new SpringApplicationBuilder(Main.class).run();
        SceneChanger.setApplicationContext(applicationContext);
    }

    @Override
    public void start(Stage stage) {
        SceneChanger.setMainStage(stage);
        SceneChanger.changeScene(RegisterPageController.getFXML());
        stage.show();
    }

   @Override
   public void stop() {
       applicationContext.close();
       Platform.exit();
   }
}
