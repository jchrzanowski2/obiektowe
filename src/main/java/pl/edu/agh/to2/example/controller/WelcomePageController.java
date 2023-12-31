package pl.edu.agh.to2.example.controller;

import javafx.event.ActionEvent;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to2.example.util.SceneChanger;

import java.net.URL;

@Controller
public class WelcomePageController {

    public static URL getFXML() {
        return WelcomePageController.class.getClassLoader().getResource("fxml/WelcomePage.fxml");
    }
    public void toLogin(ActionEvent actionEvent) {
        SceneChanger.changeScene(LoginPageController.getFXML());
    }

    public void toRegister(ActionEvent actionEvent) {
        SceneChanger.changeScene(RegisterPageController.getFXML());
    }
}
