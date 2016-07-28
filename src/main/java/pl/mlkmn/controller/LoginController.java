package pl.mlkmn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.mlkmn.model.User;
import pl.mlkmn.service.UserService;
import pl.mlkmn.validator.UserFormValidator;

@Controller
@RequestMapping("/")
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserFormValidator userFormValidator;
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(userFormValidator);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("userForm", new User());
        return "login";
    }
    
    @RequestMapping(params = "signIn", method = RequestMethod.POST)
    public String signIn(@ModelAttribute("userForm") @Validated User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        return "dashboard";
    }

    @RequestMapping(params = "signUp", method = RequestMethod.POST)
    public String signUp(@ModelAttribute("userForm") User user) {
        Errors errors = new BeanPropertyBindingResult(user, "userForm");
        userFormValidator.validate(user, errors);
        if (errors.hasErrors()) {
            return "login";
        }
        return "register";
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
