package entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="Lease")
public class Lease implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    int id;
    @Column(name = "number")
    int number;
    @Type(type="hibernate.type.LocalDateTimeUserType")
    @Column(name = "document_date")
    LocalDateTime documentDate;
    @Type(type="hibernate.type.LocalDateTimeUserType")
    @Column(name = "start_date")
    LocalDateTime startDate;
    @Type(type="hibernate.type.LocalDateTimeUserType")
    @Column(name = "end_date")
    LocalDateTime endDate;
}
