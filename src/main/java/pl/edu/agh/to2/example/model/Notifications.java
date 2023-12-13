package pl.edu.agh.to2.example.model;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Getter
@Data
public class Notifications {

    @Id
    private long id;
    private int userId;
    private String message;
    private Date sendDate;

    public Notifications(){}

    public Notifications(int userId, String message, Date sendDate){
        this.userId = userId;
        this.message = message;
        this.sendDate = sendDate;
    }

}
