package pl.edu.agh.to2.example.model;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
@Data
public class BookDetails {

    @Id
    private int bookId;
    private String author;
    private String title;
    private String cover;
    private String contents;
    private String genre;

    public BookDetails(){}

    public BookDetails(int bookId, String author, String title, String cover, String contents, String genre){
        this.bookId = bookId;
        this.author = author;
        this.title = title;
        this.cover = cover;
        this.contents = contents;
        this.genre = genre;
    }

}
