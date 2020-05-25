package nl.team2.parque_banque_server.utilities;

public class CreatePrivateAccountBackingBean {

    private String iban;
    private long balanceCent;

    public CreatePrivateAccountBackingBean() {
    }

    public CreatePrivateAccountBackingBean(String iban, long balanceCent) {
        this.iban = iban;
        this.balanceCent = balanceCent;
    }



    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public long getBalanceCent() {
        return balanceCent;
    }

    public void setBalanceCent(long balanceCent) {
        this.balanceCent = balanceCent;
    }
}
