package com.uogm.taskmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Κεντρική κλάση ρυθμίσεων ασφαλείας (Spring Security Configuration).
 * Εδώ ορίζουμε τους κανόνες πρόσβασης (Authorization), τον τρόπο ταυτοποίησης (Authentication)
 * και την κρυπτογράφηση κωδικών.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Ρύθμιση του Authentication Provider.
     * Συνδέει το Spring Security με τη βάση δεδομένων μας μέσω του UserDetailsService
     * και ορίζει τον αλγόριθμο κρυπτογράφησης (PasswordEncoder) για τον έλεγχο των κωδικών.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService); // Ποια υπηρεσία φέρνει τον χρήστη από τη DB;
        auth.setPasswordEncoder(passwordEncoder);       // Πώς ελέγχουμε αν το password είναι σωστό;
        return auth;
    }

    /**
     * Ορισμός της αλυσίδας φίλτρων ασφαλείας (Security Filter Chain).
     * Εδώ υλοποιείται το Role-Based Access Control (RBAC).
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Απενεργοποίηση CSRF για απλοποίηση της ανάπτυξης (σε παραγωγή συνήθως ενεργοποιείται)
                .csrf(AbstractHttpConfigurer::disable)

                // --- Κανόνες Εξουσιοδότησης (Authorization Rules) ---
                .authorizeHttpRequests((requests) -> requests
                        // 1. Δημόσια πρόσβαση (Whitelist): Login, Register και στατικά αρχεία (CSS, JS)
                        .requestMatchers("/register", "/login", "/css/**", "/js/**", "/images/**").permitAll()

                        // 2. Περιορισμένη πρόσβαση (RBAC): Μόνο χρήστες με ρόλο 'ADMIN' βλέπουν το /admin/**
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // 3. Όλα τα υπόλοιπα αιτήματα απαιτούν αυθεντικοποίηση (Login)
                        .anyRequest().authenticated()
                )

                // --- Ρύθμιση Φόρμας Εισόδου (Form Login) ---
                .formLogin((form) -> form
                        .loginPage("/login")           // Η δική μας custom σελίδα login
                        .loginProcessingUrl("/login")  // Το URL που υποβάλλεται η φόρμα (POST)
                        .defaultSuccessUrl("/dashboard", true) // Ανακατεύθυνση μετά από επιτυχές login
                        .permitAll()
                )

                // --- Ρύθμιση Αποσύνδεσης (Logout) ---
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout") // Επιστροφή στο login με μήνυμα
                        .permitAll()
                );

        return http.build();
    }

    /**
     * Bean για την κρυπτογράφηση κωδικών.
     * Χρησιμοποιούμε BCrypt, έναν ισχυρό αλγόριθμο hashing που περιλαμβάνει 'salt',
     * καθιστώντας τους κωδικούς ασφαλείς ακόμα και σε περίπτωση διαρροής της βάσης.
     */
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}