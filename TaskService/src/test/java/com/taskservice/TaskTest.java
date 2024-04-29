package com.taskservice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void taskCreationSuccess() {
        Task task = new Task("1", "Task One", "Description of Task One");
        assertNotNull(task);
        assertEquals("1", task.getTaskId());
        assertEquals("Task One", task.getName());
        assertEquals("Description of Task One", task.getDescription());
    }

    @Test
    void taskCreationFailure() {
        assertThrows(IllegalArgumentException.class, () -> new Task(null, "Task Two", "Description"));
        assertThrows(IllegalArgumentException.class, () -> new Task("2", null, "Description"));
        assertThrows(IllegalArgumentException.class, () -> new Task("3", "Task Three", null));
    }

    @Test
    void setNameSuccess() {
        Task task = new Task("4", "Task Four", "Description of Task Four");
        task.setName("New Name");
        assertEquals("New Name", task.getName());
    }

    @Test
    void setDescriptionSuccess() {
        Task task = new Task("5", "Task Five", "Description of Task Five");
        task.setDescription("New Description");
        assertEquals("New Description", task.getDescription());
    }

    @Test
    void setNameFailure() {
        Task task = new Task("6", "Task Six", "Description of Task Six");
        assertThrows(IllegalArgumentException.class, () -> task.setName(null));
        assertThrows(IllegalArgumentException.class, () -> task.setName("This name is way too long for the requirement"));
    }

    @Test
    void setDescriptionFailure() {
        Task task = new Task("7", "Task Seven", "Description");
        assertThrows(IllegalArgumentException.class, () -> task.setDescription(null));
        assertThrows(IllegalArgumentException.class, () -> task.setDescription("This description is way too long for the requirement"));
    }

    @Test
    void taskIdLengthExceedsLimit() {
        assertThrows(IllegalArgumentException.class, () -> new Task("12345678901", "Valid Name", "Valid Description"));
    }

    @Test
    void nameLengthExceedsLimit() {
        assertThrows(IllegalArgumentException.class, () -> new Task("8", "This name is definitely way too long for the task", "Valid Description"));
    }

    @Test
    void descriptionLengthExceedsLimit() {
        assertThrows(IllegalArgumentException.class, () -> new Task("9", "Valid Name", "This description is way too long for the requirement. It needs to be shortened for the purpose of this test being done."));
    }

}
