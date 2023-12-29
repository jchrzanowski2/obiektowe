package pl.edu.agh.to2.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.to2.example.dao.BookDAO;
import pl.edu.agh.to2.example.dao.BookDetailsDAO;
import pl.edu.agh.to2.example.dao.ReservationBookDAO;
import pl.edu.agh.to2.example.dao.ReservationsDAO;
import pl.edu.agh.to2.example.model.Book;
import pl.edu.agh.to2.example.model.BookDetails;
import pl.edu.agh.to2.example.model.LoginUser;
import pl.edu.agh.to2.example.model.Reservations;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BorrowBookService {
    @Autowired
    private BookDetailsDAO bookDetailsDAO;
    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private ReservationsDAO reservationsDAO;
    @Autowired
    private ReservationBookDAO reservationBookDAO;

    public Flux<BookDetails> getBooks() {
        return bookDetailsDAO.findAvailable();
    }

    public void submitBookReservation(LoginUser user, List<Long> bookIds) {
        LocalDate startDate = LocalDate.from(LocalDateTime.now());
        LocalDate endDate = startDate.plusMonths(1);

        Reservations reservations = reservationsDAO.save(new Reservations((int) user.getId(), startDate, endDate)).block();

        if (reservations != null) {
            for (long bookId : bookIds) {
                Book book = bookDAO.findByID(bookId).block();
                if (book == null) {
                    continue;
                }
                System.out.println(book.getQuantity());
                bookDAO.updateBook(bookId, book.getQuantity() - 1).block();
                reservationBookDAO.save((int) reservations.getId(), (int) bookId, 1).block();
            }
        }
    }
}
