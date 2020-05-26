package nl.team2.parque_banque_server.controller;

import nl.team2.parque_banque_server.model.Employee;
import nl.team2.parque_banque_server.service.EmployeeService;
import nl.team2.parque_banque_server.service.LoginService;
import nl.team2.parque_banque_server.utilities.LoginEmployeeFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@SessionAttributes("employee")
public class LoginEmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LoginService loginService;

    @GetMapping("/personeel")
    public ModelAndView loginHandler(@ModelAttribute LoginEmployeeFormBean loginEmployeeFormBean) {
        ModelAndView mav = new ModelAndView("loginemployee");
        mav.addObject("employee", loginEmployeeFormBean);
        return mav;
    }

    @PostMapping("/personeel")
    public ModelAndView employeeLoginFormHandler(@Valid LoginEmployeeFormBean loginEmployeeFormBean, BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView();

        if (bindingResult.hasErrors() || !loginService.employeeLoginValidation(loginEmployeeFormBean)) {
            mav.addObject("invalidCredentials", true);
            mav.setViewName("loginemployee");
        } else {
            Employee employee = employeeService.findByEmployeeNumber(loginEmployeeFormBean.getEmployeeNumber());
            mav.addObject("employee", employee);
            mav.setViewName("employeehome");
        }
        return mav;
    }

}
