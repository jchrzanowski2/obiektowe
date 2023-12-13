package pl.edu.agh.to2.example.model;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
@Data
public class Book {

    @Id
    private long id;
    private int quantity;
    private int rating;

    public Book(){}

    public Book(int quantity, int rating){
        this.quantity = quantity;
        this.rating = rating;
    }

}
