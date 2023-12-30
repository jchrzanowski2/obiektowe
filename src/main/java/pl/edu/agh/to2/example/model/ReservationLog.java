package pl.edu.agh.to2.example.model;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import java.time.LocalDate;

@Getter
@Data
public class ReservationLog {

    @Id
    private long id;

    private int reservationId;
    private LocalDate startDate;
    private LocalDate endDate;

    public ReservationLog(){}

    public ReservationLog(int reservationId, LocalDate startDate, LocalDate endDate){
        this.reservationId = reservationId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

}
