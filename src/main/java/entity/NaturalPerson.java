package entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class NaturalPerson implements Serializable {
    int id;
    String lastName;
    String firstName;
    String surname;
    String passportSeries;
    int passportNumber;
    LocalDateTime passportIssueDate;
    String passportIssued;
    int individualTaxNumber;
    String address;
}
