package pl.edu.agh.to2.example.model;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
@Data
public class ReservationBook {

    @Id
    private int reservationId;

    @Id
    private int bookId;
    private int quantity;

    public ReservationBook(){}

    public ReservationBook(int reservationId, int bookId, int quantity){
        this.reservationId = reservationId;
        this.bookId = bookId;
        this.quantity = quantity;
    }

}
