package nl.team2.parque_banque_server.utilities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CompanyFormBean {

    @NotBlank
    @Size(min = 8, max = 8)
    @Pattern(regexp = "^[0-9]{8}$")
    private String kvkNr;

    @NotBlank
    @Size(min = 14, max = 14)
    @Pattern(regexp = "^[A-Za-z]{2}[0-9]{9}[Bb][0-9]{2}$")
    private String btwNr;

    @NotBlank
    @Size(min = 2, max = 254)
    private String name;

    @NotBlank
    private String id;


    public CompanyFormBean() {
        super();
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

    public void setName(String name) {
        this.name = name;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CompanyFormBean{" +
                "kvkNr='" + kvkNr + '\'' +
                ", btwNr='" + btwNr + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }


}


