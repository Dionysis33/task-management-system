package com.uogm.taskmanagement.repository;

import com.uogm.taskmanagement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repository για την οντότητα Role.
 * Αποτελεί το επίπεδο πρόσβασης στα δεδομένα (Data Access Layer / DAO Pattern).
 * Κληρονομώντας το JpaRepository, αποκτούμε αυτόματα έτοιμες μεθόδους CRUD
 * (save, delete, findAll) χωρίς να γράψουμε ούτε γραμμή SQL.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Derived Query Method (Παραγόμενη Μέθοδος Ερωτήματος):
     * Το Spring "διαβάζει" το όνομα της μεθόδου (findByName) και δημιουργεί
     * δυναμικά το SQL query: SELECT * FROM roles WHERE name = ?
     * Επιστρέφει Optional για την ασφαλή διαχείριση της περίπτωσης που
     * ο ρόλος δεν βρεθεί (αποφυγή NullPointerException).
     */
    Optional<Role> findByName(String name);
}