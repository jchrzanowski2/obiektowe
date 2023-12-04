package pl.edu.agh.to2.example.model;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Getter
@Data
public class Reservations {

    @Id
    private long reservationID;

    private int userId;
    private Date startDate;
    private Date endDate;

    public Reservations(){}

    public Reservations(int userId, Date startDate, Date endDate){
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

}
