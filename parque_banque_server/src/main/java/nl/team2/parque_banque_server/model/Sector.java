package nl.team2.parque_banque_server.model;

import java.util.Objects;

public class Sector {

    private int id;
    private String name;

    public Sector() {
        super();
    }

    public Sector(int id, String name) {
        this.id = id;
        this.name = name;
    }

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

    @Override
    public String toString() {
        return "Sector{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sector)) return false;
        Sector sector = (Sector) o;
        return getId() == sector.getId() &&
                getName().equals(sector.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
