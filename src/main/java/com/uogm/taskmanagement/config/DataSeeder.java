package com.uogm.taskmanagement.config;

import com.uogm.taskmanagement.entity.Role;
import com.uogm.taskmanagement.entity.User;
import com.uogm.taskmanagement.repository.RoleRepository;
import com.uogm.taskmanagement.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

/**
 * Κλάση αρχικοποίησης δεδομένων (Database Seeding).
 * Εκτελείται αυτόματα κατά την εκκίνηση της εφαρμογής για να διασφαλίσει
 * ότι η βάση δεδομένων περιέχει τα απαραίτητα δεδομένα λειτουργίας (π.χ. Ρόλους, Admin).
 */
@Configuration
public class DataSeeder {

    /**
     * Το CommandLineRunner είναι ένα functional interface του Spring Boot που τρέχει
     * αφού φορτώσει πλήρως το Application Context.
     * Ιδανικό για να "γεμίσουμε" τη βάση με αρχικά δεδομένα (Bootstrap Data).
     */
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository,
                                   RoleRepository roleRepository,
                                   PasswordEncoder passwordEncoder) {
        return args -> {
            // --- 1. Αρχικοποίηση Ρόλων (Role Initialization) ---
            // Ελέγχουμε αν υπάρχουν οι ρόλοι για να αποφύγουμε διπλότυπα.
            // Αν δεν βρεθεί ο ρόλος, τον δημιουργούμε και τον αποθηκεύουμε.
            Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                    .orElseGet(() -> roleRepository.save(new Role(null, "ROLE_ADMIN")));

            roleRepository.findByName("ROLE_MEMBER")
                    .orElseGet(() -> roleRepository.save(new Role(null, "ROLE_MEMBER")));

            // --- 2. Δημιουργία Default Admin (Bootstrapping) ---
            // Δημιουργούμε έναν λογαριασμό διαχειριστή αν δεν υπάρχει ήδη,
            // ώστε να μπορούμε να συνδεθούμε στο σύστημα αμέσως μετά την εγκατάσταση.
            if (!userRepository.existsByUsername("admin")) {
                User admin = new User();
                admin.setUsername("admin");
                // Κρυπτογράφηση του κωδικού πριν την αποθήκευση (Security Best Practice)
                admin.setPassword(passwordEncoder.encode("Olympus2026!"));
                admin.setEmail("admin@uogm.com");
                admin.setName("System Admin");
                admin.setRoles(Set.of(adminRole)); // Ανάθεση του ρόλου ADMIN
                userRepository.save(admin);
                System.out.println("ADMIN READY: User: admin | Pass: Olympus2026!");
            }
        };
    }
}