package com.uogm.taskmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity που αναπαριστά τους Χρήστες του συστήματος.
 * Περιέχει τα στοιχεία αυθεντικοποίησης (username, password) και συνδέεται
 * με τους Ρόλους για τον καθορισμό δικαιωμάτων.
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Unique constraints: Εξασφαλίζουν την ακεραιότητα δεδομένων (Data Integrity),
    // αποτρέποντας τη δημιουργία πολλαπλών χρηστών με το ίδιο όνομα ή email.
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    private String name;

    // --- Σχέση Πολλά-προς-Πολλά (Many-to-Many) ---
    // Ένας χρήστης μπορεί να έχει πολλούς ρόλους και ένας ρόλος να ανήκει σε πολλούς χρήστες.
    //
    // 1. FetchType.EAGER: Φορτώνουμε τους ρόλους αμέσως μόλις φορτωθεί ο χρήστης.
    // Αυτό είναι απαραίτητο για το Spring Security ώστε να ελέγξει τα δικαιώματα κατά το Login.
    //
    // 2. Απουσία CascadeType.ALL (Σημαντικό):
    // Αφαιρέθηκε σκόπιμα το Cascade. Αν διαγράψουμε έναν χρήστη, ΔΕΝ θέλουμε
    // να διαγραφεί και ο Ρόλος (π.χ. ADMIN) από τη βάση δεδομένων, καθώς μπορεί να τον έχουν κι άλλοι.
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles", // Ο ενδιάμεσος πίνακας σύνδεσης
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    // --- Σχέση Ένα-προς-Πολλά (One-to-Many) με Tasks ---
    // ΠΡΟΣΘΗΚΗ: Αυτό έλειπε και προκαλούσε το Error 500 στη διαγραφή.
    // mappedBy = "user": Αναφέρεται στο πεδίο 'user' μέσα στην κλάση Task.
    // cascade = CascadeType.ALL: Όταν διαγράφεται ο User, διαγράφονται ΑΥΤΟΜΑΤΑ και τα Tasks του.
    // orphanRemoval = true: Καθαρίζει τα "ορφανά" tasks από τη βάση.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<Task> tasks;
}