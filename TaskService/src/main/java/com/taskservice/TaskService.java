package com.taskservice;

import java.util.HashMap;
import java.util.Map;

public class TaskService {
    private final Map<String, Task> tasks = new HashMap<>();

    public void addTask(Task task) {
        if (task == null || tasks.containsKey(task.getTaskId())) {
            throw new IllegalArgumentException("Task cannot be null and ID must be unique.");
        }
        tasks.put(task.getTaskId(), task);
    }

    public void deleteTask(String taskId) {
        if (!tasks.containsKey(taskId)) {
            throw new IllegalArgumentException("Task ID does not exist.");
        }
        tasks.remove(taskId);
    }

    public void updateTask(String taskId, String name, String description) {
        Task task = tasks.get(taskId);
        if (task == null) {
            throw new IllegalArgumentException("Task ID does not exist.");
        }
        task.setName(name);
        task.setDescription(description);
    }

    public Task getTask(String taskId) {
        return tasks.get(taskId);
    }
}
