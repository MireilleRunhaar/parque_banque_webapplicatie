package nl.team2.parque_banque_server.utilities;

import nl.team2.parque_banque_server.model.PrivateAccount;
import nl.team2.parque_banque_server.service.IbanService;
import org.springframework.beans.factory.annotation.Autowired;

public class CreatePrivateAccountBackingBean {

    public final long START_SALDO=0;

    @Autowired
    private IbanService ibanService;

    private String iban;
    private long balanceCent;

    public CreatePrivateAccountBackingBean() {
    }

    public CreatePrivateAccountBackingBean(String iban, long balanceCent) {
        this.iban = ibanService.createNewIban();
        this.balanceCent = START_SALDO;
    }

    public PrivateAccount privateAccount(){
        PrivateAccount privateAccount=new PrivateAccount(ibanService.createNewIban(),START_SALDO);
        return privateAccount;
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
