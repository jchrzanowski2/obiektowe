package pl.edu.agh.to2.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Roles {

    @Id
    private String roleName;
    private String description;

    public Roles(){}

    public Roles(String roleName, String description){
        this.roleName = roleName;
        this.description = description;
    }

    public String getRoleName(){return this.roleName;}

    public String getDescription(){return this.description;}
}
