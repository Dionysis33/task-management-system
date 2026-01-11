package com.uogm.taskmanagement.service;

import com.uogm.taskmanagement.entity.Task;
import com.uogm.taskmanagement.entity.User;
import com.uogm.taskmanagement.repository.TaskRepository;
import com.uogm.taskmanagement.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation (Η Υλοποίηση).
 * Εδώ γράφουμε τον κώδικα που κάνει τη δουλειά.
 */
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Task> findTasksByUser(String username) {
        return taskRepository.findByUserUsername(username);
    }

    @Override
    public void saveTask(Task task, String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            task.setUser(user);
            taskRepository.save(task);
        }
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void toggleTaskStatus(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            task.setCompleted(!task.isCompleted());
            taskRepository.save(task);
        }
    }

    @Override
    public Task findTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }
}