package pl.edu.agh.to2.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity
public class ReservationLog {

    @Id
    @GeneratedValue
    private long reservationLogID;

    @ManyToOne
    private Reservations reservation;
    private Date startDate;
    private Date endDate;

    public ReservationLog(){}

    public ReservationLog(Reservations reservation, Date startDate, Date endDate){
        this.reservation = reservation;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getReservationLogID(){return this.reservationLogID;}

    public Reservations getReservation(){return this.reservation;}

    public Date getStartDate(){return this.startDate;}

    public Date getEndDate(){return this.endDate;}
}
