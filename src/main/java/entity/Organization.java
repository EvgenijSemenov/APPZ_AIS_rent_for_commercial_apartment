package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Organization")
public class Organization {

    @Id
    @GeneratedValue
    @Column(name = "id")
    int id;

    @Column(name = "name")
    String name;

    @Column(name = "short_name")
    String shortName;

    @Column(name = "address")
    String address;

    @Column(name = "contact_number")
    String contactNumber;

    @Column(name = "email", unique = true)
    String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "organization", cascade = CascadeType.ALL)
    private List<Apartment> apartmentList;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "organization", cascade = CascadeType.ALL)
    private List<Lease> leaseList;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "organization", cascade = CascadeType.ALL)
    private List<LegalPerson> legalPersonListList;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "organization", cascade = CascadeType.ALL)
    private List<NaturalPerson> naturalPersonList;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "organization", cascade = CascadeType.ALL)
    private List<Employee> employeeList;

    public Organization() {}

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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Apartment> getApartmentList() {
        return apartmentList;
    }

    public void setApartmentList(List<Apartment> apartmentList) {
        this.apartmentList = apartmentList;
    }

    public List<Lease> getLeaseList() {
        return leaseList;
    }

    public void setLeaseList(List<Lease> leaseList) {
        this.leaseList = leaseList;
    }

    public List<LegalPerson> getLegalPersonListList() {
        return legalPersonListList;
    }

    public void setLegalPersonListList(List<LegalPerson> legalPersonListList) {
        this.legalPersonListList = legalPersonListList;
    }

    public List<NaturalPerson> getNaturalPersonList() {
        return naturalPersonList;
    }

    public void setNaturalPersonList(List<NaturalPerson> naturalPersonList) {
        this.naturalPersonList = naturalPersonList;
    }
}
