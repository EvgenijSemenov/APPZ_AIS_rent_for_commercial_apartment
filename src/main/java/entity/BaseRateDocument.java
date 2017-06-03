package entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="Base_rate_document")
public class BaseRateDocument implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    int id;
    @Column(name = "number")
    int number;
    @Type(type="hibernate.type.LocalDateTimeUserType")
    @Column(name = "date")
    LocalDateTime date;
    @Column(name = "rate")
    float rate;
}
