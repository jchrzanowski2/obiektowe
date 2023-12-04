package pl.edu.agh.to2.example.model;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
@Data
public class Roles {

    @Id
    private String roleName;
    private String description;

    public Roles(){}

    public Roles(String roleName, String description){
        this.roleName = roleName;
        this.description = description;
    }

}
