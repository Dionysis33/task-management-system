package com.uogm.taskmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

/**
 * Entity που αναπαριστά μια Εργασία (Task) στη βάση δεδομένων.
 * Αντιστοιχίζεται στον πίνακα 'tasks' και περιέχει όλη την πληροφορία
 * που απαιτείται από την εκφώνηση (Τίτλος, Προτεραιότητα, Κατηγορία).
 */
@Entity
@Table(name = "tasks")
@Data // Το Lombok παράγει αυτόματα Getters, Setters, equals(), hashcode() κλπ.
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;
    private LocalDate dueDate;
    private boolean completed;

    // Η προτεραιότητα της εργασίας (LOW, MEDIUM, HIGH)
    private String priority;

    // Πεδίο που προστέθηκε για να καλύψει την απαίτηση "Categorization" της εργασίας.
    // Επιτρέπει την οργάνωση των εργασιών σε ομάδες (π.χ. Work, Personal) και την αναζήτηση βάσει αυτών.
    private String category;

    // Συσχέτιση Πολλά-προς-Ένα (Many-to-One):
    // Πολλές εργασίες ανήκουν σε έναν Χρήστη.
    // Δημιουργεί το Foreign Key 'user_id' στη βάση, εξασφαλίζοντας την ιδιωτικότητα δεδομένων (Data Isolation).
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}