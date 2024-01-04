package pl.edu.agh.to2.example.model;

import lombok.Data;
import lombok.Getter;
@Getter
@Data
public class ReservationBook {

    private int reservationid;

    private int bookid;
    private int quantity;

    public ReservationBook(){}

    public ReservationBook(int reservationId, int bookId, int quantity){
        this.reservationid = reservationId;
        this.bookid = bookId;
        this.quantity = quantity;
    }

    public int getBookId() {
        return bookid;
    }

    public int getQuantity() {
        return quantity;
    }
}
