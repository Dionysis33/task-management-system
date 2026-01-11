package com.uogm.taskmanagement.service;

import com.uogm.taskmanagement.dto.UserRegistrationDTO;
import com.uogm.taskmanagement.entity.User;
import java.util.List;

/**
 * Service Interface για τη διαχείριση χρηστών.
 * Ορίζει τις επιχειρηματικές λειτουργίες που αφορούν τους χρήστες (Εγγραφή, Λίστα, Διαγραφή).
 * Η χρήση Interface επιτρέπει την αφαίρεση (Abstraction) και διευκολύνει μελλοντικές αλλαγές στον κώδικα.
 */
public interface UserService {

    /**
     * Εγγραφή νέου χρήστη στο σύστημα.
     * Δέχεται ως όρισμα το DTO (UserRegistrationDTO) και όχι το Entity,
     * υλοποιώντας την ασφαλή μεταφορά δεδομένων από τη φόρμα εγγραφής.
     *
     * @param registrationDto Τα δεδομένα εγγραφής (username, password, κλπ).
     */
    void saveUser(UserRegistrationDTO registrationDto);

    /**
     * Επιστρέφει τη λίστα όλων των χρηστών.
     * Χρησιμοποιείται από τον πίνακα ελέγχου του Διαχειριστή (Admin Panel).
     */
    List<User> findAllUsers();

    /**
     * Διαγράφει έναν χρήστη από τη βάση δεδομένων βάσει του ID του.
     * Προσοχή: Η διαγραφή είναι μόνιμη.
     */
    void deleteUser(Long id);
}