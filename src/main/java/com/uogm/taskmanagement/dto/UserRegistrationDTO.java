package com.uogm.taskmanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * Data Transfer Object (DTO) για την εγγραφή νέου χρήστη.
 * Σχεδιαστικό Πρότυπο (Design Pattern):
 * Χρησιμοποιούμε DTOs αντί για τα Entities (π.χ. User) απευθείας στον Controller για δύο λόγους:
 * 1. Ασφάλεια: Αποφεύγουμε το "Over-posting" ή "Mass Assignment Vulnerability".
 * (π.χ. ο χρήστης δεν μπορεί να στείλει κρυφά πεδίο "role": "ADMIN").
 * 2. Decoupling: Η δομή της φόρμας (View) διαφέρει από τη δομή της βάσης.
 * Για παράδειγμα, το 'confirmPassword' χρειάζεται για έλεγχο αλλά δεν αποθηκεύεται.
 */
@Data
public class UserRegistrationDTO {

    // Validation Annotations (Jakarta Validation):
    // Εξασφαλίζουν την ακεραιότητα των δεδομένων (Data Integrity) ΠΡΙΝ φτάσουν στο Business Logic.

    @NotEmpty(message = "Το username είναι υποχρεωτικό")
    private String username;

    @NotEmpty(message = "Ο κωδικός είναι υποχρεωτικός")
    private String password;

    // Πεδίο αποκλειστικά για τη διεπαφή χρήστη (UI Logic). Δεν υπάρχει στον πίνακα 'users'.
    @NotEmpty(message = "Η επιβεβαίωση κωδικού είναι υποχρεωτική")
    private String confirmPassword;

    @Email(message = "Δώστε ένα έγκυρο email") // Έλεγχος μορφής email (Regex)
    @NotEmpty(message = "Το email είναι υποχρεωτικό")
    private String email;

    @NotEmpty(message = "Το όνομα είναι υποχρεωτικό")
    private String name;
}