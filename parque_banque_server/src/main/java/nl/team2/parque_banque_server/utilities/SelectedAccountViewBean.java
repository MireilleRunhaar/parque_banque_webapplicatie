package nl.team2.parque_banque_server.utilities;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class SelectedAccountViewBean {

    @NotBlank
    String oppositeAccountIban;

    @NotBlank
    String amount;

    @NotBlank
    String description;

    @NotBlank
    LocalDate date;

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
