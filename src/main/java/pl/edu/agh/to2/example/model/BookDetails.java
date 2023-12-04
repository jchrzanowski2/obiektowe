package pl.edu.agh.to2.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class BookDetails {

    @Id
    @OneToOne
    private Book book;
    private String author;
    private String title;
    private String cover;
    private String contents;
    private String genre;

    public BookDetails(){}

    public BookDetails(Book book, String author, String title, String cover, String contents, String genre){
        this.book = book;
        this.author = author;
        this.title = title;
        this.cover = cover;
        this.contents = contents;
        this.genre = genre;
    }

    public Book getBook(){return this.book;}

    public String getAuthor(){return this.author;}

    public String getTitle(){return this.title;}

    public String getCover(){return this.cover;}

    public String getContents(){return this.contents;}

    public String getGenre(){return this.genre;}

}
