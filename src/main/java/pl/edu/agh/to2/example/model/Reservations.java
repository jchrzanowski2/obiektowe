package pl.edu.agh.to2.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity
public class Reservations {

    @Id
    @GeneratedValue
    private long reservationID;

    @ManyToOne
    private User user;
    private Date startDate;
    private Date endDate;

    public Reservations(){}

    public Reservations(User user, Date startDate, Date endDate){
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getReservationID(){return this.reservationID;}

    public User getUser(){return this.user;}

    public Date getStartDate(){return this.startDate;}

    public Date getEndDate(){return this.endDate;}

}
