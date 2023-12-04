package pl.edu.agh.to2.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ReservationBook {

    @Id
    @ManyToOne
    private Reservations reservation;

    @Id
    @ManyToOne
    private Book book;
    private int quantity;

    public ReservationBook(){}

    public ReservationBook(Reservations reservation, Book book, int quantity){
        this.reservation = reservation;
        this.book = book;
        this.quantity = quantity;
    }

    public Reservations getReservation(){return this.reservation;}

    public Book getBook(){return this.book;}

    public int getQuantity(){return this.quantity;}
}
