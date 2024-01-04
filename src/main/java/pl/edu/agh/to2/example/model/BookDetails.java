package pl.edu.agh.to2.example.model;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
@Data
public class BookDetails {

    @Id
    private long id;
    private String author;
    private String title;
    private String cover;
    private String contents;
    private String genre;

    public BookDetails(){}

    public BookDetails(String author, String title, String cover, String contents, String genre){
        this.author = author;
        this.title = title;
        this.cover = cover;
        this.contents = contents;
        this.genre = genre;
    }

    public long getId() {
        return id;
    }

    public String getFullTitle() {
        return id + ": " + title + ", " + author;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getCover() {
        return cover;
    }

    public String getContents() {
        return contents;
    }

    public String getGenre() {
        return genre;
    }
}
