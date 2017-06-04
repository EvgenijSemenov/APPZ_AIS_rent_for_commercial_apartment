package entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="Legal_person")
public class LegalPerson implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    int id;
    @Column(name = "name")
    String name;
    @Column(name = "address")
    String address;
    @Column(name = "individual_tax_number")
    int individualTaxNumber;
    @Column(name = "license_number")
    int licenseNumber;
    @Type(type="hibernate.type.LocalDateTimeUserType")
    @Column(name = "license_date")
    LocalDateTime licenseDate;
}
