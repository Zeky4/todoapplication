package com.app.todo.controller;
import com.app.todo.models.Task;
import com.app.todo.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TaskController {
    private final TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping("/")
    public String getTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();  // Fetch tasks from the service
        model.addAttribute("tasks", tasks);  // Add tasks to the model
        model.addAttribute("pageTitle", "Task Management System");
        model.addAttribute("headerText", "Your To-Do List");
        return "tasks";
    }


    @GetMapping("/tasks/add")
    public String showAddTaskForm(Model model) {
        model.addAttribute("pageTitle", "Add New Task");
        model.addAttribute("headerText", "Add a Task to Your List");
        return "add-task";
    }


    @PostMapping("/tasks/save")
    public String saveTask(@RequestParam String title) {
        Task task = new Task();
        task.setTitle(title);
        task.setCompleted(false);
        taskService.saveTask(task);
        return "redirect:/";
    }


    @PostMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/";
    }
}

