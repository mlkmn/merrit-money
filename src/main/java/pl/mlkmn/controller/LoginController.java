package pl.mlkmn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.mlkmn.enums.Page;
import pl.mlkmn.model.User;
import pl.mlkmn.service.UserService;
import pl.mlkmn.validator.UserFormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserFormValidator userFormValidator;
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(userFormValidator);
    }
    
    @RequestMapping(value = {"/", "login"}, method = RequestMethod.GET)
    public String login(Model model,
                        @RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout) {

        if (error != null) {
            model.addAttribute("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addAttribute("msg", "You've been logged out successfully.");
        }
        model.addAttribute("userForm", new User());
        return Page.LOGIN.getName();
    }

    @RequestMapping(params = "signUp")
    public String signUp(@ModelAttribute("userForm") @Validated User user,
                         BindingResult bindingResult,
                         Model model) {
        
        if (bindingResult.hasErrors()) {
            return Page.LOGIN.getName();
        }

        String requestedLogin = user.getLogin();
        User loggedUser = userService.findByLogin(requestedLogin);

        if (loggedUser != null && loggedUser.getLogin().equals(user.getLogin())) {
            model.addAttribute("msg", "User \"" + requestedLogin + "\" already registered, choose different login");
            return Page.LOGIN.getName();
        } else {
            userService.saveOrUpdate(user);
            model.addAttribute("msg", "User \"" + requestedLogin + "\" registered, await activation.");
            return Page.LOGIN.getName();
        }
    }

    @RequestMapping(value = "/adminPanel", method = RequestMethod.GET)
    public String admin() {
        return Page.ADMIN_PANEL.getName();
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.POST)
    public String dashboardPost() {
        return Page.DASHBOARD.getName();
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboardGet() {
        return Page.DASHBOARD.getName();
    }

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public String accessDenied() {
        return Page.ACCESS_DENIED.getName();
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout (HttpServletRequest request,
                              HttpServletResponse response,
                              Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        model.addAttribute("msg", "Logged out successfully.");
        model.addAttribute("userForm", new User());
        return Page.LOGIN.getName();
    }
}
