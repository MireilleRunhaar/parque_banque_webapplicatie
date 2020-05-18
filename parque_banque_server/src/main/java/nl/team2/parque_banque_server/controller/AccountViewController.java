package nl.team2.parque_banque_server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountViewController {

    @GetMapping
    public String accountView(){
        return "accountview";

    }

    //koppelen aan knoppen etc
}
