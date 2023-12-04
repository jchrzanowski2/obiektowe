package pl.edu.agh.to2.example.model;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Getter
@Data
public class ReservationLog {

    @Id
    private long reservationLogID;

    private int reservationId;
    private Date startDate;
    private Date endDate;

    public ReservationLog(){}

    public ReservationLog(int reservationId, Date startDate, Date endDate){
        this.reservationId = reservationId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

}
