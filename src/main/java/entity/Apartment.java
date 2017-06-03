package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Apartment")
public class Apartment implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    int id;
    @Column(name = "address")
    String address;
    @Column(name = "area")
    float area;
    @Column(name = "basement_area")
    float basementArea;
    @Column(name = "basement_rate")
    float basementRate;
    @Column(name = "technical_equipment_rate")
    float technicalEquipmentRate;
}
