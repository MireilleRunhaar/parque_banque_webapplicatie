package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.BusinessAccount;
import nl.team2.parque_banque_server.model.Employee;
import nl.team2.parque_banque_server.service.BusinessAccountService;
import nl.team2.parque_banque_server.service.EmployeeService;
import nl.team2.parque_banque_server.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@SessionAttributes("employeeId")
public class OverviewBusinessAccountsController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    private BusinessAccountService businessAccountService;

    @GetMapping("/overzicht-bedrijven")
    public String handleOverviewBusiness(Model model) {
        //Create an employee from the session attribute
        Employee employee = employeeService.findEmployeeBySAId(model.getAttribute("employeeId"));

        if(!model.containsAttribute("employeeId")) {
            return "loginemployee";
        } else if (employee.getRole().getId() != 2)   {
            return "redirect:/personeel-home";
        } else {
            return "overviewbusinessaccount";
        }
    }

    //Saldo
    @RequestMapping(value = "/overzicht-bedrijven", params = "Saldo", method = RequestMethod.POST)
    public ModelAndView top10BalanceBusinessAccounts(){
        ModelAndView mav=new ModelAndView("tenrichestbusinessaccounts");
        Map<Long,Object[]> richestBusinessCustomers=statisticsService.getTenRichestBusinessCustomers();
        mav.addObject("richestBusinessCustomers",richestBusinessCustomers);
        return mav;
    }

    //Sector
    @RequestMapping(value = "/overzicht-bedrijven", params = "Sectoren", method = RequestMethod.POST)
    public ModelAndView averageBalanceSector(){
        ModelAndView mav=new ModelAndView("averagebalancesector");
        Iterable<BusinessAccount> businessAccounts=businessAccountService.findAll();
        Map<Long,Object> averageBalanceSector=statisticsService.averageBalanceSector(businessAccounts);
        mav.addObject("averageBalanceSector",averageBalanceSector);
        return mav;
    }

    @RequestMapping(value = "/overzicht-bedrijven",params = "Transacties",method = RequestMethod.POST)
    public ModelAndView top10MostTransactionAccounts(){
        ModelAndView mav=new ModelAndView("mostactivebusinessaccounts");
        Map<Long,Object> mostActiveBusinessAccounts=statisticsService.getTenMostActiveBusinessCustomers();
        mav.addObject("mostActiveBusinessAccounts",mostActiveBusinessAccounts);
        return mav;
    }

}

