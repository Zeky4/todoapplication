package com.app.todo.services;

import com.app.todo.models.Task;
import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    void saveTask(Task task);
    void deleteTask(Long id);
}