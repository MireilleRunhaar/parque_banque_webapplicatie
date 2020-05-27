package nl.team2.parque_banque_server.utilities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CompanyFormBean {

    @NotBlank
    @Size(min = 8, max = 8)
    private String kvkNr;

    @NotBlank
    @Size(min = 12, max = 12)
    private String btwNr;

    @NotBlank
    @Size(min = 2, max = 30)
    private String companyName;

    @NotBlank
    @Size(min = 2, max = 30)
    private String sectorName;


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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    @Override
    public String toString() {
        return "CompanyFormBean{" +
                "kvkNr='" + kvkNr + '\'' +
                ", btwNr='" + btwNr + '\'' +
                ", companyName='" + companyName + '\'' +
                ", sectorName='" + sectorName + '\'' +
                '}';
    }


}


