package pl.edu.agh.to2.example.model;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Data
public class Reservations {

    @Id
    private long id;

    private int userid;
    private LocalDate startdate;
    private LocalDate enddate;

    public Reservations(){}

    public Reservations(int userId, LocalDate startDate, LocalDate endDate){
        this.userid = userId;
        this.startdate = startDate;
        this.enddate = endDate;
    }

    public long getId() {
        return id;
    }

    public String getDateRange() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        return startdate.format(formatter) + " - " + enddate.format(formatter);
    }
}
