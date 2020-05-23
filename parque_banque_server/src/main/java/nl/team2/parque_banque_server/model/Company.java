package nl.team2.parque_banque_server.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Company {

    @Id
    private int id;

    private String btwNr;
    private String kvkNr;
    private String name;
    private Sector sector;


    public Company() {
        super();
    }

    public Company(String btwNr, String kvkNr, String name, Sector sector) {
        this.id=0;
        this.btwNr = btwNr;
        this.kvkNr = kvkNr;
        this.name = name;
        this.sector = sector;
    }

    public String getBtwNr() {
        return btwNr;
    }

    public void setBtwNr(String btwNr) {
        this.btwNr = btwNr;
    }

    public String getKvkNr() {
        return kvkNr;
    }

    public void setKvkNr(String kvkNr) {
        this.kvkNr = kvkNr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                "BTWnr='" + btwNr + '\'' +
                ", KVKnr='" + kvkNr + '\'' +
                ", name='" + name + '\'' +
                ", sector=" + sector +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company company = (Company) o;
        return getBtwNr().equals(company.getBtwNr()) &&
                getKvkNr().equals(company.getKvkNr()) &&
                getName().equals(company.getName()) &&
                getSector().equals(company.getSector());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBtwNr(), getKvkNr(), getName(), getSector());
    }
}
