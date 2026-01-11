package com.uogm.taskmanagement.service;

import com.uogm.taskmanagement.entity.Task;
import com.uogm.taskmanagement.entity.User;
import com.uogm.taskmanagement.repository.TaskRepository;
import com.uogm.taskmanagement.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    // --- TEST 1: Έλεγχος Αποθήκευσης (Save Task) ---
    @Test
    void saveTask_successfully() {
        // 1. Setup
        String username = "student1";
        User mockUser = new User();
        mockUser.setUsername(username);

        Task task = new Task();
        task.setTitle("Test Unit Task");

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));

        // 2. Action
        taskService.saveTask(task, username);

        // 3. Assertion
        assertEquals(mockUser, task.getUser());
        verify(taskRepository, times(1)).save(task);
    }

    // --- TEST 2: Έλεγχος Εύρεσης Εργασιών (Find by User) ---
    @Test
    void findTasksByUser_returnsList() {
        String username = "student1";
        Task t1 = new Task(); t1.setTitle("Task A");
        Task t2 = new Task(); t2.setTitle("Task B");
        List<Task> mockTasks = Arrays.asList(t1, t2);

        when(taskRepository.findByUserUsername(username)).thenReturn(mockTasks);

        List<Task> result = taskService.findTasksByUser(username);

        assertEquals(2, result.size());

        // Η ΑΛΛΑΓΗ ΕΙΝΑΙ ΕΔΩ: Χρησιμοποιούμε .getFirst() αντί για .get(0)
        assertEquals("Task A", result.getFirst().getTitle());
    }

    // --- TEST 3: Έλεγχος Διαγραφής (Delete Task) ---
    @Test
    void deleteTask_verifiesCall() {
        Long taskId = 99L;

        taskService.deleteTask(taskId);

        verify(taskRepository, times(1)).deleteById(taskId);
    }
}