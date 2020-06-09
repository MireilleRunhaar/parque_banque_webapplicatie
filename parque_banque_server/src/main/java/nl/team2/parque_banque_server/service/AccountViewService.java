package nl.team2.parque_banque_server.service;

import nl.team2.parque_banque_server.model.BusinessAccount;
import nl.team2.parque_banque_server.model.PrivateAccount;
import nl.team2.parque_banque_server.utilities.AccountViewListBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountViewService {

    @Autowired
    PaymentAccountService paymentAccountService;

    public List<AccountViewListBean> convertPrivateAccountList(List<PrivateAccount> privateAccountList) {
        List<AccountViewListBean> privateAccountViewList = new ArrayList<>();
        for (PrivateAccount privateAccount : privateAccountList) {
            AccountViewListBean bean = new AccountViewListBean();
            bean.setIban(privateAccount.getIban());
            bean.setBalanceEuros(paymentAccountService.balanceInEuros(privateAccount.getBalance()));
            privateAccountViewList.add(bean);
        }
        return privateAccountViewList;
    }

    public List<AccountViewListBean> convertBusinessAccountList(List<BusinessAccount> businessAccountList) {
        List<AccountViewListBean> businessAccountViewList = new ArrayList<>();
        for (BusinessAccount businessAccount : businessAccountList) {
            AccountViewListBean bean = new AccountViewListBean();
            bean.setIban(businessAccount.getIban());
            bean.setBalanceEuros(paymentAccountService.balanceInEuros(businessAccount.getBalance()));
            businessAccountViewList.add(bean);
        }
        return businessAccountViewList;
    }
}
