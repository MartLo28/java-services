package com.taskservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService();
    }

    @Test
    void addTaskSuccess() {
        Task task = new Task("1", "Initial Task", "This is a test task.");
        taskService.addTask(task);
        assertNotNull(taskService.getTask("1"));
    }

    @Test
    void addTaskDuplicateId() {
        Task task1 = new Task("1", "First Task", "Description of the first task.");
        taskService.addTask(task1);
        Task task2 = new Task("1", "Duplicate Task", "This task has a duplicate ID.");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            taskService.addTask(task2);
        });

        String expectedMessage = "Task cannot be null and ID must be unique.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void deleteTaskSuccess() {
        Task task = new Task("2", "Task To Delete", "Description of the task to delete.");
        taskService.addTask(task);
        taskService.deleteTask("2");
        assertNull(taskService.getTask("2"));
    }

    @Test
    void deleteNonexistentTask() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            taskService.deleteTask("nonexistent");
        });

        String expectedMessage = "Task ID does not exist.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void updateTaskSuccess() {
        Task task = new Task("3", "Task To Update", "Initial Description");
        taskService.addTask(task);
        taskService.updateTask("3", "Updated Name", "Updated Description");

        Task updatedTask = taskService.getTask("3");
        assertEquals("Updated Name", updatedTask.getName());
        assertEquals("Updated Description", updatedTask.getDescription());
    }

    @Test
    void updateNonexistentTask() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            taskService.updateTask("nonexistent", "Name", "Description");
        });

        String expectedMessage = "Task ID does not exist.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
