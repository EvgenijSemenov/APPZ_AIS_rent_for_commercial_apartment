package entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="Lease")
public class Lease implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    int id;
    @Column(name = "number", nullable = false)
    int number;
    @Type(type="hibernate.type.LocalDateTimeUserType")
    @Column(name = "document_date", nullable = false)
    LocalDateTime documentDate;
    @Type(type="hibernate.type.LocalDateTimeUserType")
    @Column(name = "start_date", nullable = false)
    LocalDateTime startDate;
    @Type(type="hibernate.type.LocalDateTimeUserType")
    @Column(name = "end_date", nullable = false)
    LocalDateTime endDate;
    @ManyToOne
    @JoinColumn(name = "apartment_id", referencedColumnName = "id")
    private Apartment apartment;
    @ManyToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    private Organization organization;
    @ManyToOne
    @JoinColumn(name = "legal_person_renter_id", referencedColumnName = "id")
    private LegalPerson legalPersonRenter;
    @ManyToOne
    @JoinColumn(name = "natural_person_renter_id", referencedColumnName = "id")
    private NaturalPerson naturalPersonRenter;

    Lease() {}

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

    public LocalDateTime getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(LocalDateTime documentDate) {
        this.documentDate = documentDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public LegalPerson getLegalPersonRenter() {
        return legalPersonRenter;
    }

    public void setLegalPersonRenter(LegalPerson legalPersonRenter) {
        this.legalPersonRenter = legalPersonRenter;
    }

    public NaturalPerson getNaturalPersonRenter() {
        return naturalPersonRenter;
    }

    public void setNaturalPersonRenter(NaturalPerson naturalPersonRenter) {
        this.naturalPersonRenter = naturalPersonRenter;
    }
}
