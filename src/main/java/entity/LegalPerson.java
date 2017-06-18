package entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="Legal_person")
public class LegalPerson {
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "legalPersonRenter", cascade = CascadeType.ALL)
    List<Lease> leaseList;

    @ManyToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    private Organization organization;

    public LegalPerson() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIndividualTaxNumber() {
        return individualTaxNumber;
    }

    public void setIndividualTaxNumber(int individualTaxNumber) {
        this.individualTaxNumber = individualTaxNumber;
    }

    public int getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(int licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public LocalDateTime getLicenseDate() {
        return licenseDate;
    }

    public void setLicenseDate(LocalDateTime licenseDate) {
        this.licenseDate = licenseDate;
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
