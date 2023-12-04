package pl.edu.agh.to2.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity
public class Notifications {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private User user;
    private String message;
    private Date sendDate;

    public Notifications(){}

    public Notifications(User user, String message, Date sendDate){
        this.user = user;
        this.message = message;
        this.sendDate = sendDate;
    }

    public long getId(){return this.id;}

    public User getUser(){return this.user;}

    public String getMessage(){return this.message;}

    public Date getSendDate(){return this.sendDate;}
}
