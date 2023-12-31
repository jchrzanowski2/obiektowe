package pl.edu.agh.to2.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

@Getter
@Data
@AllArgsConstructor
public class Book implements Persistable<Long> {

    @Id
    private Long id;
    private int quantity;
    private int rating;
    @Transient
    @Builder.Default
    private boolean isNewEntry = true;
    public Book(){}

    public Book(Long bookDetailId, int quantity, int rating) {
        this.id = bookDetailId;
        this.quantity = quantity;
        this.rating = rating;
    }

    @Override
    public Long getId() {
        return id;
    }

    public boolean isNew() {
        return isNewEntry;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getRating() {
        return rating;
    }
}
