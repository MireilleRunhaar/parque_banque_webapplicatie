package nl.team2.parque_banque_server.utilities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class LinkAccountFormBean {

   @NotBlank
   private String iban;


   @NotBlank
   @Pattern(regexp = "[0-9]{5}")
   private String securityCode;

   public LinkAccountFormBean(){super();}

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }


    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }
}
