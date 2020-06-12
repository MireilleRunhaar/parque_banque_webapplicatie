package nl.team2.parque_banque_server.utilities;

import javax.validation.constraints.NotBlank;

public class SelectedAccountViewBean {

    @NotBlank
    String oppositeAccountIban;

    @NotBlank
    String amount;

    @NotBlank
    String description;

    @NotBlank
   String date;

    public SelectedAccountViewBean() {
        super();
    }

    public String getOppositeAccountIban() {
        return oppositeAccountIban;
    }

    public void setOppositeAccountIban(String oppositeAccountIban) {
        this.oppositeAccountIban = oppositeAccountIban;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
