package pl.edu.agh.to2.example.model;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue
    private long userID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @ManyToOne
    private Roles role;

    public User(){}

    public User(String firstName, String lastName, String email, String password, Roles role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public long getUserID(){return this.userID;}

    public String getFirstName(){return this.firstName;}

    public String getLastName(){return this.lastName;}

    public String getEmail(){return this.email;}

    public String getPassword(){return this.password;}

    public Roles getRole() { return this.role; }
}
