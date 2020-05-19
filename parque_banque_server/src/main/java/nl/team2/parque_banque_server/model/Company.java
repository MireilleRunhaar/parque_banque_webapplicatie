package nl.team2.parque_banque_server.model;

import java.util.Objects;

public class Company {

    private String BTWnr;
    private String KVKnr;
    private String name;
    private Sector sector;


    public Company() {
        super();
    }

    public Company(String BTWnr, String KVKnr, String name, Sector sector) {
        this.BTWnr = BTWnr;
        this.KVKnr = KVKnr;
        this.name = name;
        this.sector = sector;
    }

    public String getBTWnr() {
        return BTWnr;
    }

    public void setBTWnr(String BTWnr) {
        this.BTWnr = BTWnr;
    }

    public String getKVKnr() {
        return KVKnr;
    }

    public void setKVKnr(String KVKnr) {
        this.KVKnr = KVKnr;
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
                "BTWnr='" + BTWnr + '\'' +
                ", KVKnr='" + KVKnr + '\'' +
                ", name='" + name + '\'' +
                ", sector=" + sector +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company company = (Company) o;
        return getBTWnr().equals(company.getBTWnr()) &&
                getKVKnr().equals(company.getKVKnr()) &&
                getName().equals(company.getName()) &&
                getSector().equals(company.getSector());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBTWnr(), getKVKnr(), getName(), getSector());
    }
}
