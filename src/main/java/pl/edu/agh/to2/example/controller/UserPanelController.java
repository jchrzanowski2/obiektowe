package pl.edu.agh.to2.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.reactive.TransactionalOperator;
import pl.edu.agh.to2.example.event.LoginEvent;
import pl.edu.agh.to2.example.model.BookDetails;
import pl.edu.agh.to2.example.model.LoginUser;
import pl.edu.agh.to2.example.service.PermissionService;
import pl.edu.agh.to2.example.service.RegisterService;
import pl.edu.agh.to2.example.service.UserPanelService;

import java.net.URL;

@Controller
public class UserPanelController implements ApplicationListener<LoginEvent> {
    public VBox reservationPanel;
    public VBox reservationList;
    @FXML
    private Label firstNameError;
    @FXML
    private Label lastNameError;
    @FXML
    private Label passwordError;
    @FXML
    private TextField password;
    @FXML
    private TextField lastName;
    @FXML
    private TextField firstName;
    @FXML
    private TextField email;
    @FXML
    private TextField role;

    LoginUser user;

    @Autowired
    public RegisterService registerService;
    @Autowired
    public UserPanelService userPanelService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private TransactionalOperator transactionalOperator;

    public static URL getFXML() {
        return UserPanelController.class.getClassLoader().getResource("fxml/UserPanel.fxml");
    }

    @FXML
    public void initialize() {
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        email.setText(user.getEmail());
        password.setText(user.getPassword());
        role.setText(userPanelService.getRoleNameForUser(user));

        if (permissionService.canBorrowBooks(user)) {
            userPanelService.getReservationsForUser(user).toStream().forEach(reservations -> {
                VBox item = new VBox();
                Label label = new Label(reservations.getDateRange());
                label.getStyleClass().add("labels");
                item.getChildren().add(label);
                VBox bookList = new VBox();
                userPanelService.getReservationBooks(reservations).toStream().forEach(reservationBook -> {
                    BookDetails bookDetails = userPanelService.getBookDetailsForReservationBook(reservationBook);
                    bookList.getChildren().add(new Label("(qty: " + reservationBook.getQuantity() + ") " + bookDetails.getFullTitle()));
                });
                item.getChildren().add(bookList);
                reservationList.getChildren().add(item);
            });
            reservationPanel.setVisible(true);
        }

        clearErrorLabels();
    }

    public void fieldChange(KeyEvent keyEvent) {
        if (keyEvent.getSource() instanceof TextField field) {
            clearErrorLabels();

            boolean firstNameCorrect = registerService.validateFirstName(firstName.getText());
            boolean lastNameCorrect = registerService.validateLastName(lastName.getText());
            boolean passwordCorrect = registerService.validatePassword(password.getText());

            if (!firstNameCorrect) {
                firstNameError.setVisible(true);
            }
            if (!lastNameCorrect) {
                lastNameError.setVisible(true);
            }
            if (!passwordCorrect) {
                passwordError.setVisible(true);
            }

            if (field.getId().equals(firstName.getId()) && firstNameCorrect) {
                user = userPanelService.changeFirstName(user, firstName.getText());
            } else if (field.getId().equals(lastName.getId()) && lastNameCorrect) {
                user = userPanelService.changeLastName(user, lastName.getText());
            } else if (field.getId().equals(password.getId()) && passwordCorrect) {
                user = userPanelService.changePassword(user, password.getText());
            }
        }
    }

    private void clearErrorLabels() {
        firstNameError.setVisible(false);
        lastNameError.setVisible(false);
        passwordError.setVisible(false);
    }

    @Override
    public void onApplicationEvent(LoginEvent event) {
        user = (LoginUser) event.getSource();
    }
}