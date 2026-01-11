package com.uogm.taskmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity που αναπαριστά τους Ρόλους χρηστών στη βάση δεδομένων.
 * Αποτελεί θεμελιώδες κομμάτι του συστήματος ασφαλείας (RBAC - Role Based Access Control),
 * καθορίζοντας τα δικαιώματα πρόσβασης (Authorization) κάθε χρήστη.
 */
@Entity
@Table(name = "roles")
// Lombok Annotations: Αυτοματοποιούν τη δημιουργία Getters, Setters, Constructors
// μειώνοντας δραματικά τον επαναλαμβανόμενο κώδικα (Boilerplate Code).
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Το όνομα του ρόλου (π.χ. ROLE_ADMIN, ROLE_MEMBER).
    // Ορίζεται ως unique ώστε να μην υπάρχουν διπλότυποι ρόλοι στη βάση.
    @Column(nullable = false, unique = true)
    private String name;
}