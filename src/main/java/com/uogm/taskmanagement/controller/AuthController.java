package com.uogm.taskmanagement.controller;

import com.uogm.taskmanagement.dto.UserRegistrationDTO;
import com.uogm.taskmanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller υπεύθυνος για τη διαχείριση της αυθεντικοποίησης.
 */
@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UserRegistrationDTO user = new UserRegistrationDTO();
        model.addAttribute("user", user);
        return "auth/register";
    }

    /**
     * Υποβολή της φόρμας εγγραφής.
     * @param registrationDto Τα δεδομένα που συμπλήρωσε ο χρήστης.
     * @param result Περιέχει τα αποτελέσματα του Validation.
     * (Το annotation @Valid ενεργοποιεί τους ελέγχους που ορίσαμε στο DTO).
     */
    @PostMapping("/register")
    public String registerUserAccount(@Valid @ModelAttribute("user") UserRegistrationDTO registrationDto,
                                      BindingResult result) {
        if (result.hasErrors()) {
            return "auth/register";
        }
        try {
            userService.saveUser(registrationDto);
            return "redirect:/register?success";
        } catch (Exception e) {
            result.rejectValue("email", "error.user", "Υπήρξε σφάλμα κατά την εγγραφή.");
            return "auth/register";
        }
    }
}