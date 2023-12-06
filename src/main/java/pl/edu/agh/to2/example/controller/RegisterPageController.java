package pl.edu.agh.to2.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to2.example.model.LoginUser;
import pl.edu.agh.to2.example.service.RegisterService;

import java.net.URL;

@Controller
public class RegisterPageController {
    @FXML
    private Label firstNameErrorLabel;
    @FXML
    private Label lastNameErrorLabel;
    @FXML
    private Label emailErrorLabel;
    @FXML
    private Label passwordErrorLabel;
    @FXML
    private TextField password;
    @FXML
    private TextField email;
    @FXML
    private TextField lastname;
    @FXML
    private TextField firstname;

    @Autowired
    public RegisterService registerService;

    public static URL getFXML() {
        return RegisterPageController.class.getClassLoader().getResource("fxml/RegisterPanel.fxml");
    }

    public void register(ActionEvent actionEvent) {
        clearErrorLabels();

        boolean firstNameCorrect = registerService.validateFirstName(firstname.getText());
        boolean lastNameCorrect = registerService.validateLastName(lastname.getText());
        boolean emailCorrect = registerService.validateEmail(email.getText());
        boolean passwordCorrect = registerService.validatePassword(password.getText());

        if (!firstNameCorrect) {
            firstNameErrorLabel.setVisible(true);
        }
        if (!lastNameCorrect) {
            lastNameErrorLabel.setVisible(true);
        }
        if (!emailCorrect) {
            emailErrorLabel.setVisible(true);
        }
        if (!passwordCorrect) {
            passwordErrorLabel.setVisible(true);
        }

        if (firstNameCorrect && lastNameCorrect && emailCorrect && passwordCorrect) {
            System.out.println("Added user");
            registerService.addUser(new LoginUser(firstname.getText(), lastname.getText(), email.getText(), password.getText(), 2));
        }
    }

    private void clearErrorLabels() {
        firstNameErrorLabel.setVisible(false);
        lastNameErrorLabel.setVisible(false);
        emailErrorLabel.setVisible(false);
        passwordErrorLabel.setVisible(false);
    }
}
