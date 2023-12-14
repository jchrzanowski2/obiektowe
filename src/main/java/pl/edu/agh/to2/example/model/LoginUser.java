package pl.edu.agh.to2.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
@Data
@AllArgsConstructor
public class LoginUser {

    @Id
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private long roleid;

    public LoginUser(){

    }

    public LoginUser(String firstName, String lastName, String email, String password, Long roleid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roleid = roleid;
    }

}