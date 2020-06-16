package nl.team2.parque_banque_server.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Company {

    @Id
    private String kvkNr;
    private String btwNr;
    private String name;

    @ManyToOne (cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Sector sector;


    public Company() {
        super();
    }

    public Company(String kvkNr, String btwNr, String name, Sector sector) {
        this.kvkNr = kvkNr;
        this.btwNr = btwNr;
        this.name = name;
        this.sector = sector;
    }

    public String getKvkNr() {
        return kvkNr;
    }

    public void setKvkNr(String kvkNr) {
        this.kvkNr = kvkNr;
    }

    public String getBtwNr() {
        return btwNr;
    }

    public void setBtwNr(String btwNr) {
        this.btwNr = btwNr;
    }

    public String getName() {
        return name;
    }

    public void setName(String companyName) {
        this.name = companyName;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    @Override
    public String toString() {
        return "Company{" +
                "kvkNr='" + kvkNr + '\'' +
                ", btwNr='" + btwNr + '\'' +
                ", companyName='" + name + '\'' +
                ", sector=" + sector +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company company = (Company) o;
        return getKvkNr().equals(company.getKvkNr()) &&
                getBtwNr().equals(company.getBtwNr()) &&
                getName().equals(company.getName()) &&
                getSector().equals(company.getSector());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBtwNr(), getKvkNr(), getName(), getSector());
    }
}
