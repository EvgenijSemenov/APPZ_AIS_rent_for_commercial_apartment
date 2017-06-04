package entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="Natural_person")
public class NaturalPerson implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    int id;
    @Column(name = "last_name")
    String lastName;
    @Column(name = "first_name")
    String firstName;
    @Column(name = "surname")
    String surname;
    @Column(name = "passport_series")
    String passportSeries;
    @Column(name = "passport_number")
    int passportNumber;
    @Type(type="hibernate.type.LocalDateTimeUserType")
    @Column(name = "passport_issue_date")
    LocalDateTime passportIssueDate;
    @Column(name = "passport_issued")
    String passportIssued;
    @Column(name = "individual_tax_number")
    int individualTaxNumber;
    @Column(name = "address")
    String address;
}
