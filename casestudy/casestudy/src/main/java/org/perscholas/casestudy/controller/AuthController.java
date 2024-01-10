package org.perscholas.casestudy.controller;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.casestudy.formbean.RegisterUserFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.perscholas.casestudy.service.UserService;
import org.springframework.web.servlet.ModelAndView;
import org.perscholas.casestudy.database.entity.User;
import org.perscholas.casestudy.service.AuthUserService;



@Slf4j
@Controller
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthUserService authUserService;
    @GetMapping("/auth/login")
    public ModelAndView login(){
        ModelAndView response = new ModelAndView();
        response.setViewName("auth/login");
        return response;
    }

    @GetMapping("/auth/register")
    public ModelAndView register() {
        ModelAndView response = new ModelAndView();
        response.setViewName("auth/register");
        return response;
    }

    @GetMapping("/auth/registerSubmit")
    public ModelAndView registerSubmit(@Valid RegisterUserFormBean form, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            log.info("######################### In register user - has errors #########################");
            ModelAndView response = new ModelAndView("auth/register");

            for (ObjectError error : bindingResult.getAllErrors()) {
                log.info("error: " + error.getDefaultMessage());
            }

            response.addObject("form", form);
            response.addObject("errors", bindingResult);
            return response;
        }

        log.info("######################### In register user - no error found #########################");

        User u = userService.createNewUser(form);

        // this line of code will authenticate the brand new user to the application
        // the session we are passing into this method as an argument and spring boot is automatically managing the session
        // and is able to figure out the new argument to the controller method and populate it with the correct session
        authUserService.authenticateNewUser(session, u.getEmail(), form.getPassword());

        // the view name can either be a jsp file name or a redirect to another controller method
        ModelAndView response = new ModelAndView();
        response.setViewName("redirect:/auth/login");

        return response;
    }
    // Additional methods for handling login/logout using Spring Security
}