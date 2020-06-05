package nl.team2.parque_banque_server.utilities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LinkAccountFormBean {

   @NotBlank
   private String iban;

   @NotBlank
    private String safetycode;

   public LinkAccountFormBean(){super();}

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getSafetycode() {
        return safetycode;
    }

    public void setSafetycode(String safetycode) {
        this.safetycode = safetycode;
    }
}
