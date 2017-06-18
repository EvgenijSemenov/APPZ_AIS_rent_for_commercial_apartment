package entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="Natural_person")
public class NaturalPerson {

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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "naturalPersonRenter", cascade = CascadeType.ALL)
    List<Lease> leaseList;

    @ManyToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    private Organization organization;

    public NaturalPerson() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    public LocalDateTime getPassportIssueDate() {
        return passportIssueDate;
    }

    public void setPassportIssueDate(LocalDateTime passportIssueDate) {
        this.passportIssueDate = passportIssueDate;
    }

    public String getPassportIssued() {
        return passportIssued;
    }

    public void setPassportIssued(String passportIssued) {
        this.passportIssued = passportIssued;
    }

    public int getIndividualTaxNumber() {
        return individualTaxNumber;
    }

    public void setIndividualTaxNumber(int individualTaxNumber) {
        this.individualTaxNumber = individualTaxNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Lease> getLeaseList() {
        return leaseList;
    }

    public void setLeaseList(List<Lease> leaseList) {
        this.leaseList = leaseList;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
