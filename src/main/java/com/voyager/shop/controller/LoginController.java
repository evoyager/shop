package com.voyager.shop.controller;

import com.voyager.shop.model.Contact;
import com.voyager.shop.model.User;
import com.voyager.shop.service.ContactService;
import com.voyager.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value="/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByLogin(user.getLogin());

        if (userExists != null) {
            bindingResult
                    .rejectValue("login", "error.user",
                            "There is already a user registered with the login provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");
        }

        return modelAndView;
    }

    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByLogin(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " " + user.getMiddleName() + " (" + user.getLogin() + ")");
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

    @RequestMapping(value="/admin/createNewContact", method = RequestMethod.POST)
    public ModelAndView createNewContact(@Valid Contact contact, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Contact contactExists = contactService.findContactByEmail(contact.getEmail());
        if (contactExists != null) {
            bindingResult
                .rejectValue("email", "error.contact",
                    "There is already a contact in phone book with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("admin/createNewContact");
        } else {
            contactService.saveContact(contact);
            modelAndView.addObject("successMessage", "Contact has been added to phone book successfully");
            modelAndView.addObject("contact", new Contact());
            modelAndView.setViewName("admin/createNewContact");
    }
        return modelAndView;
    }

}
