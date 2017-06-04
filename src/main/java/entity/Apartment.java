package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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
    @ManyToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    private Organization organization;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "apartment", cascade = CascadeType.ALL)
    private List<Lease> leaseList;

    Apartment() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public float getBasementArea() {
        return basementArea;
    }

    public void setBasementArea(float basementArea) {
        this.basementArea = basementArea;
    }

    public float getBasementRate() {
        return basementRate;
    }

    public void setBasementRate(float basementRate) {
        this.basementRate = basementRate;
    }

    public float getTechnicalEquipmentRate() {
        return technicalEquipmentRate;
    }

    public void setTechnicalEquipmentRate(float technicalEquipmentRate) {
        this.technicalEquipmentRate = technicalEquipmentRate;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public List<Lease> getLeaseList() {
        return leaseList;
    }

    public void setLeaseList(List<Lease> leaseList) {
        this.leaseList = leaseList;
    }
}
