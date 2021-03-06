package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Employee;
import nl.team2.parque_banque_server.service.EmployeeService;
import nl.team2.parque_banque_server.service.LoginService;
import nl.team2.parque_banque_server.utilities.LoginEmployeeFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@SessionAttributes("employeeId")
public class LoginEmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LoginService loginService;

    @GetMapping("/personeel")
    public String loginHandler(@ModelAttribute LoginEmployeeFormBean loginEmployeeFormBean,
                               Model model) {
        // Checks if there isn't already a valid active employeeId in the application
        if (model.getAttribute("employeeId") != null) {
            return "redirect:/personeel-home";
        } else {
            model.addAttribute("employeeId", loginEmployeeFormBean);
            return "loginemployee";
        }
    }

    @PostMapping("/personeel")
    public String employeeLoginFormHandler(@Valid LoginEmployeeFormBean loginEmployeeFormBean,
                                           BindingResult bindingResult,
                                           Model model) {
        // Checks the bean validation of the input and if the login is valid
        if (bindingResult.hasErrors() || !loginService.employeeLoginValidation(loginEmployeeFormBean)) {
            model.addAttribute("invalidCredentials", true);
            return "loginemployee";
        } else {
            Employee employee = employeeService.findByEmployeeNumber(loginEmployeeFormBean.getEmployeeNumber());
            model.addAttribute("employeeId", employee.getId());
            return "redirect:/personeel-home";
        }
    }

    @GetMapping("/uitloggen-personeel")
    public String employeeLogOuthandler(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
