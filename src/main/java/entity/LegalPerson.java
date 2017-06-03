package entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class LegalPerson implements Serializable {
    int id;
    String name;
    String address;
    int individualTaxNumber;
    int licenseNumber;
    LocalDateTime licenseDate;
}
