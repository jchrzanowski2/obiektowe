package pl.edu.agh.to2.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to2.example.service.RegisterService;

import java.net.URL;

@Controller
@ComponentScan(basePackages = "pl.edu.agh.to2.example.service")
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
    public RegisterService registerService;

    public static URL getFXML() {
        return RegisterPageController.class.getClassLoader().getResource("fxml/RegisterPanel.fxml");
    }

    public void register(ActionEvent actionEvent) {
        boolean firstNameCorrect = registerService.registerFirstName(firstname.getText());
        boolean lastNameCorrect = registerService.registerLastName(lastname.getText());
        boolean emailCorrect = registerService.registerEmail(email.getText());
        boolean passwordCorrect = registerService.registerPassword(password.getText());
        firstNameErrorLabel.setVisible(!firstNameCorrect);
        lastNameErrorLabel.setVisible(!lastNameCorrect);
        EmailErrorLabel.setVisible(!emailCorrect);
        PasswordErrorLabel.setVisible(!passwordCorrect);
        if(firstNameCorrect && lastNameCorrect && emailCorrect && passwordCorrect){
            //registerService.addUser(new User(firstname.getText(), lastname.getText(), email.getText(), password.getText(), "user"));
        }
    }
}
