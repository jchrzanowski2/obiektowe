package pl.edu.agh.to2.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.to2.example.dao.*;
import pl.edu.agh.to2.example.model.BookDetails;
import pl.edu.agh.to2.example.model.LoginUser;
import pl.edu.agh.to2.example.model.ReservationBook;
import pl.edu.agh.to2.example.model.Reservations;
import reactor.core.publisher.Flux;

@Service
public class UserPanelService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RolesDAO rolesDAO;
    @Autowired
    private ReservationsDAO reservationsDAO;
    @Autowired
    private ReservationBookDAO reservationBookDAO;
    @Autowired
    private BookDetailsDAO bookDetailsDAO;

    public LoginUser changeFirstName(LoginUser user, String newValue) {
        return userDAO.updateFirstName(user.getId(), newValue)
                .then(userDAO.findByEmail(user.getEmail()))
                .blockOptional().orElse(user);
    }

    public LoginUser changeLastName(LoginUser user, String newValue) {
        return userDAO.updateLastName(user.getId(), newValue)
                .then(userDAO.findByEmail(user.getEmail()))
                .blockOptional().orElse(user);
    }

    public LoginUser changePassword(LoginUser user, String newValue) {
        return userDAO.updatePassword(user.getId(), newValue)
                .then(userDAO.findByEmail(user.getEmail()))
                .blockOptional().orElse(user);
    }

    public String getRoleNameForUser(LoginUser user) {
        return rolesDAO.findById(user.getRoleid()).block().getRoleName();
    }

    public Flux<Reservations> getReservationsForUser(LoginUser user) {
        return reservationsDAO.findByUserId(user.getId());
    }

    public Flux<ReservationBook> getReservationBooks(Reservations reservations) {
        return reservationBookDAO.findByReservationId((int) reservations.getId());
    }

    public BookDetails getBookDetailsForReservationBook(ReservationBook reservationBook) {
        return bookDetailsDAO.findByID((long) reservationBook.getBookId()).block();
    }
}
