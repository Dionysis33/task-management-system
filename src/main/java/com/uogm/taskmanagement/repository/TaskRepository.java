package com.uogm.taskmanagement.repository;

import com.uogm.taskmanagement.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

/**
 * Repository για την οντότητα Task.
 * Παρέχει έτοιμες μεθόδους για την ανάκτηση και αποθήκευση εργασιών.
 * Όλες οι μέθοδοι αναζήτησης περιλαμβάνουν το 'UserUsername' για να διασφαλιστεί
 * η ιδιωτικότητα των δεδομένων (κάθε χρήστης βλέπει μόνο τα δικά του).
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Επιστρέφει όλες τις εργασίες που ανήκουν στον συγκεκριμένο χρήστη.
     * Το Spring Data JPA παράγει αυτόματα το query:
     * SELECT * FROM tasks t JOIN users u ON t.user_id = u.id WHERE u.username = ?
     */
    List<Task> findByUserUsername(String username);

    /**
     * Σύνθετη αναζήτηση βάσει Κατηγορίας.
     * Λέξεις-κλειδιά:
     * - And: Συνδυάζει τον έλεγχο χρήστη με την κατηγορία.
     * - Containing: Αντιστοιχεί στο SQL 'LIKE %query%' (μερικό ταίριασμα).
     * - IgnoreCase: Αγνοεί πεζά/κεφαλαία (Case Insensitive) για καλύτερο UX.
     */
    List<Task> findByUserUsernameAndCategoryContainingIgnoreCase(String username, String category);

    /**
     * Αναζήτηση εργασιών με συγκεκριμένη ημερομηνία λήξης.
     * Χρησιμοποιείται για το φιλτράρισμα στο Dashboard.
     */
    List<Task> findByUserUsernameAndDueDate(String username, LocalDate dueDate);
}