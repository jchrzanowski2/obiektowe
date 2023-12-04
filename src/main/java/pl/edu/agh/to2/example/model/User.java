package pl.edu.agh.to2.example.model;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
@Data
public class User {

    @Id
    private long userID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;

    public User(){}

    public User(String firstName, String lastName, String email, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

}
