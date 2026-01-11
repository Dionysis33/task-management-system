package com.uogm.taskmanagement.controller;

import com.uogm.taskmanagement.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller αποκλειστικά για ενέργειες Διαχειριστή (Administrator).
 * Περιλαμβάνει τη διαχείριση χρηστών (User Management).
 * * Σημείωση Ασφαλείας:
 * Όλα τα endpoints κάτω από το "/admin" προστατεύονται από το Spring Security (SecurityConfig),
 * επιτρέποντας πρόσβαση μόνο σε χρήστες με ρόλο 'ADMIN' (RBAC).
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    // Dependency Injection μέσω Constructor (Best Practice για testability και immutability)
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Ανακτά και εμφανίζει τη λίστα όλων των εγγεγραμμένων χρηστών στο σύστημα.
     * Τα δεδομένα μεταφέρονται στο View (admin/users.html) μέσω του αντικειμένου Model.
     */
    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "admin/users";
    }

    /**
     * Διαγράφει έναν χρήστη από τη βάση δεδομένων.
     * @param id Το ID του χρήστη που λαμβάνεται δυναμικά από το URL (@PathVariable).
     * Μετά τη διαγραφή, χρησιμοποιείται το pattern 'Redirect' για επιστροφή στη λίστα.
     */
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users"; // Redirect για αποφυγή επαναυποβολής φόρμας (PRG Pattern)
    }
}