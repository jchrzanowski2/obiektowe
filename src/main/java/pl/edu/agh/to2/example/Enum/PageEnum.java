package pl.edu.agh.to2.example.Enum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.edu.agh.to2.example.controller.AddBookController;
import pl.edu.agh.to2.example.controller.BooksPageController;

import java.net.URL;

@RequiredArgsConstructor
@Getter
public enum PageEnum {
    ADD_BOOK("add_book", AddBookController.getFXML()),
    BOOKS("books", BooksPageController.getFXML());
    private final String name;
    private final URL url;
}
