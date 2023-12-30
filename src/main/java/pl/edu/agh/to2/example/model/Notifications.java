package pl.edu.agh.to2.example.model;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Getter
@Data
public class Notifications {

    @Id
    private long id;
    private int userId;
    private String message;
    private LocalDate sendDate;

    public Notifications(){}

    public Notifications(int userId, String message, LocalDate sendDate){
        this.userId = userId;
        this.message = message;
        this.sendDate = sendDate;
    }

}
