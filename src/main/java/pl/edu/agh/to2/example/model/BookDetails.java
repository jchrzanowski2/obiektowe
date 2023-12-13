package pl.edu.agh.to2.example.model;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
@Data
public class BookDetails {

    @Id
    private int id;
    private String author;
    private String title;
    private String cover;
    private String contents;
    private String genre;

    public BookDetails(){}

    public BookDetails(int id, String author, String title, String cover, String contents, String genre){
        this.id = id;
        this.author = author;
        this.title = title;
        this.cover = cover;
        this.contents = contents;
        this.genre = genre;
    }

}
