package com.uogm.taskmanagement.service;

import com.uogm.taskmanagement.entity.Task;
import java.util.List;

/**
 * Service Interface (Το Συμβόλαιο).
 * Εδώ δηλώνουμε ΜΟΝΟ τις μεθόδους, χωρίς κώδικα μέσα τους.
 */
public interface TaskService {

    List<Task> findTasksByUser(String username);

    void saveTask(Task task, String username);

    void deleteTask(Long id);

    void toggleTaskStatus(Long id);

    Task findTaskById(Long id);
}