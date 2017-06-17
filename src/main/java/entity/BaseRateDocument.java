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

    BaseRateDocument() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
