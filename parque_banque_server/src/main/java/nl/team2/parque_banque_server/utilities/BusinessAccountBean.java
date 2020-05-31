package nl.team2.parque_banque_server.utilities;

import java.util.Objects;

public class BusinessAccountBean {

   private String kvk;

    public BusinessAccountBean() {
    }

    public String getKvk() {
        return kvk;
    }

    public void setKvk(String kvk) {
        this.kvk = kvk;
    }

    @Override
    public String toString() {
        return "BusinessAccountBean{" +
                "kvk='" + kvk + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BusinessAccountBean)) return false;
        BusinessAccountBean that = (BusinessAccountBean) o;
        return getKvk().equals(that.getKvk());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKvk());
    }
}
