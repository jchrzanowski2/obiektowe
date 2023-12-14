package pl.edu.agh.to2.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to2.example.event.LoginEvent;
import pl.edu.agh.to2.example.model.LoginUser;
import pl.edu.agh.to2.example.service.LoginService;
import pl.edu.agh.to2.example.util.SceneChanger;

import java.net.URL;

@Controller
public class LoginPageController {
    @FXML
    private Label emailErrorLabel;
    @FXML
    private Label passwordErrorLabel;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField password;
    @FXML
    private TextField email;

    @Autowired
    private LoginService loginService;
    @Autowired
    private ApplicationContext applicationContext;
    public static URL getFXML() {
        return LoginPageController.class.getClassLoader().getResource("fxml/LoginPanel.fxml");
    }

    public void login(ActionEvent actionEvent) {
        clearErrorLabels();

        boolean emailCorrect = loginService.validateEmail(email.getText());
        boolean passwordCorrect = loginService.validatePassword(password.getText());
        if (!emailCorrect) {
            emailErrorLabel.setVisible(true);
        }
        if (!passwordCorrect) {
            passwordErrorLabel.setVisible(true);
        }
        if(emailCorrect && passwordCorrect){
            LoginUser user = loginService.userExists(email.getText(), password.getText());
            if(user != null){
                applicationContext.publishEvent(new LoginEvent(user));
                errorLabel.setVisible(false);
                SceneChanger.changeScene(MainPageController.getFXML());
            }
            else{
                errorLabel.setVisible(true);
            }
        }
        else{
            errorLabel.setVisible(true);
        }
    }

    private void clearErrorLabels() {
        emailErrorLabel.setVisible(false);
        passwordErrorLabel.setVisible(false);
    }
}
