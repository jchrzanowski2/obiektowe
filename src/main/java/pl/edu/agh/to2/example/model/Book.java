package pl.edu.agh.to2.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private long bookID;
    private int quantity;
    private int rating;

    public Book(){}

    public Book(int quantity, int rating){
        this.quantity = quantity;
        this.rating = rating;
    }

    public long getBookID() {return this.bookID;}

    public int getQuantity(){return this.quantity;}

    public int getRating(){return this.rating;}
}
