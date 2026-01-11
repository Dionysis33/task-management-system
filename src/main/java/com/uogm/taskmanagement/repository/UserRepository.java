package com.uogm.taskmanagement.repository;

import com.uogm.taskmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repository για την διαχείριση των Χρηστών (User Entity).
 * Μας επιτρέπει να εκτελούμε ενέργειες στη βάση (CRUD) για τους χρήστες,
 * λειτουργώντας ως γέφυρα μεταξύ της βάσης δεδομένων και του Spring Security.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Εύρεση χρήστη βάσει username.
     * Χρησιμοποιείται κυρίως από το UserDetailsService κατά τη διαδικασία του Login
     * για να ανακτήσει τα στοιχεία του χρήστη (κωδικό, ρόλους).
     * Επιστρέφει Optional<> για να διαχειριστούμε με ασφάλεια την περίπτωση
     * που ο χρήστης δεν υπάρχει (αποφυγή NullPointerException).
     */
    Optional<User> findByUsername(String username);

    /**
     * Έλεγχος ύπαρξης χρήστη.
     * Χρησιμοποιείται κατά την Εγγραφή (Registration) για Validation.
     * Πριν δημιουργήσουμε νέο χρήστη, ελέγχουμε αν το username είναι πιασμένο.
     * Είναι πολύ πιο αποδοτικό (Optimized Query) από το να ανακτούσαμε ολόκληρο το αντικείμενο.
     */
    Boolean existsByUsername(String username);
}