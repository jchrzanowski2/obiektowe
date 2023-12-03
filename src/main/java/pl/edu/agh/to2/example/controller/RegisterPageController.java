package pl.edu.agh.to2.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to2.example.event.RegisterEvent;
import pl.edu.agh.to2.example.service.RegisterService;
import pl.edu.agh.to2.example.util.SceneChanger;

import java.net.URL;

@Controller
public class RegisterPageController {
    @FXML
    private Label firstNameErrorLabel;
    @FXML
    private Label lastNameErrorLabel;
    @FXML
    private Label EmailErrorLabel;
    @FXML
    private Label PasswordErrorLabel;
    @FXML
    private TextField password;
    @FXML
    private TextField email;
    @FXML
    private TextField lastname;
    @FXML
    private TextField firstname;
    @Autowired
    private RegisterService registerService = new RegisterService();
    @Autowired
    private ApplicationContext applicationContext;

    public static URL getFXML() {
        return RegisterPageController.class.getClassLoader().getResource("fxml/RegisterPanel.fxml");
    }

    public void register(ActionEvent actionEvent) {
        boolean firstNameCorrect = registerService.registerFirstName(firstname.getText());
        boolean lastNameCorrect = registerService.registerFirstName(lastname.getText());
        boolean emailCorrect = registerService.registerFirstName(email.getText());
        boolean passwordCorrect = registerService.registerFirstName(password.getText());
        firstNameErrorLabel.setVisible(!firstNameCorrect);
        lastNameErrorLabel.setVisible(!lastNameCorrect);
        EmailErrorLabel.setVisible(!emailCorrect);
        PasswordErrorLabel.setVisible(!passwordCorrect);
        if(firstNameCorrect && lastNameCorrect && emailCorrect && passwordCorrect){
            System.out.println("Correct!");
        }
    }
}
