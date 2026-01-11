package com.uogm.taskmanagement.controller;

import com.uogm.taskmanagement.entity.Task;
import com.uogm.taskmanagement.repository.TaskRepository;
import com.uogm.taskmanagement.service.TaskService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

/**
 * Controller υπεύθυνος για τη διαχείριση του κύκλου ζωής των εργασιών (Task Lifecycle).
 * Υλοποιεί το πρότυπο MVC, δεχόμενος αιτήματα HTTP και επιστρέφοντας τα κατάλληλα Views.
 * Διαχειρίζεται όλες τις CRUD ενέργειες (Create, Read, Update, Delete) για τα Tasks.
 */
@Controller
public class TaskController {

    private final TaskService taskService;
    private final TaskRepository taskRepository;

    // Constructor Injection: Βέλτιστη πρακτική για την εισαγωγή εξαρτήσεων (Dependencies),
    // διευκολύνοντας το testing και εξασφαλίζοντας ότι τα services δεν είναι null.
    public TaskController(TaskService taskService, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }

    /**
     * Η κύρια μέθοδος του Dashboard.
     * Διαχειρίζεται την εμφάνιση των εργασιών, την αναζήτηση (Search/Filtering)
     * και τον υπολογισμό των ειδοποιήσεων (Notifications).
     *
     * @param principal Παρέχει πρόσβαση στον τρέχοντα συνδεδεμένο χρήστη (Security Context).
     * @param searchCategory Προαιρετική παράμετρος για φιλτράρισμα βάσει κατηγορίας.
     */
    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal,
                            @RequestParam(required = false) String searchCategory,
                            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate searchDate) {
        // Ανάκτηση του username του αυθεντικοποιημένου χρήστη για να φέρουμε μόνο τα δικά του δεδομένα (Data Isolation).
        String username = principal.getName();
        List<Task> tasks;

        // Λογική Αναζήτησης: Ελέγχουμε αν υπάρχουν παράμετροι φίλτρων στο URL
        // και καλούμε τις αντίστοιχες μεθόδους του Repository.
        if (searchCategory != null && !searchCategory.isEmpty()) {
            tasks = taskRepository.findByUserUsernameAndCategoryContainingIgnoreCase(username, searchCategory);
        } else if (searchDate != null) {
            tasks = taskRepository.findByUserUsernameAndDueDate(username, searchDate);
        } else {
            // Αν δεν υπάρχει αναζήτηση, επιστρέφουμε όλες τις ενεργές εργασίες του χρήστη.
            tasks = taskService.findTasksByUser(username);
        }

        // --- Business Logic για Notifications ---
        // Χρήση Java Streams API για τον υπολογισμό των εκπρόθεσμων (Overdue) εργασιών.
        // Φιλτράρουμε τις εργασίες που δεν έχουν ολοκληρωθεί ΚΑΙ η ημερομηνία τους είναι πριν από σήμερα.
        long overdueCount = tasks.stream()
                .filter(t -> !t.isCompleted() && t.getDueDate() != null && t.getDueDate().isBefore(LocalDate.now()))
                .count();

        // Πέρασμα δεδομένων στο View (Thymeleaf)
        model.addAttribute("tasks", tasks);
        model.addAttribute("overdueCount", overdueCount);
        model.addAttribute("newTask", new Task()); // Κενό αντικείμενο για τη φόρμα "New Mission"
        return "dashboard";
    }

    /**
     * Αποθήκευση νέας εργασίας.
     * Συνδέουμε την εργασία με τον χρήστη που είναι συνδεδεμένος (Principal) για ασφάλεια.
     */
    @PostMapping("/addTask")
    public String addTask(@ModelAttribute("newTask") Task task, Principal principal) {
        taskService.saveTask(task, principal.getName());
        return "redirect:/dashboard"; // Redirect για αποφυγή διπλής υποβολής (Post/Redirect/Get Pattern)
    }

    // Διαγραφή εργασίας βάσει ID
    @GetMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/dashboard";
    }

    // Αλλαγή κατάστασης (Pending <-> Completed)
    @GetMapping("/toggleTask/{id}")
    public String toggleTaskStatus(@PathVariable Long id) {
        taskService.toggleTaskStatus(id);
        return "redirect:/dashboard";
    }

    // Προετοιμασία της φόρμας επεξεργασίας (Edit View)
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Task task = taskService.findTaskById(id);
        if (task == null) return "redirect:/dashboard";
        model.addAttribute("task", task);
        return "edit_task";
    }

    // Ενημέρωση υπάρχουσας εργασίας
    @PostMapping("/updateTask")
    public String updateTask(@ModelAttribute("task") Task task, Principal principal) {
        taskService.saveTask(task, principal.getName());
        return "redirect:/dashboard";
    }
}