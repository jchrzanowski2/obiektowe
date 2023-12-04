package pl.edu.agh.to2.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import pl.edu.agh.to2.example.controller.RegisterPageController;
import pl.edu.agh.to2.example.util.SceneChanger;

@SpringBootApplication
@ComponentScan(basePackages = "pl.edu.agh.to2.example.controller")
public class App extends Application  {
    private ConfigurableApplicationContext applicationContext;
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    @Override
    public void init() {
        applicationContext = new SpringApplicationBuilder(App.class).run();
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
