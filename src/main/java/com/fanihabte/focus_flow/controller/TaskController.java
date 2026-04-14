package com.fanihabte.focus_flow.controller;

import com.fanihabte.focus_flow.entity.Task;
import com.fanihabte.focus_flow.enums.TaskStatus;
import com.fanihabte.focus_flow.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TaskController {

    private final TaskService taskService;

    TaskController (TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String homePage (Model model) {
        Map<String, Object> attributes = new HashMap<>();

        attributes.put("countOfOpenTasks", taskService.countOfOpenTasks());
        attributes.put("completePercentage", taskService.completionPercentage());
        attributes.put("nextUp", taskService.getNearestCurrentDateTask());
        attributes.put("todayTasks", taskService.getCurrentDateTask());
        attributes.put("tasks", taskService.getAllTasks());
        attributes.put("completedTasks", taskService.getCompletedTasks());
        attributes.put("newTaskForm", new Task());

        model.addAllAttributes(attributes);

        return "index";
    }

    // This method creates a task from form input
    @PostMapping("/tasks")
    public String createTask (@ModelAttribute Task task) {
        taskService.createNewTask(task);
        return "redirect:/";
    }

    // This method updated the status of task to complete
    @PostMapping("/tasks/{id}/complete")
    public String completeTask (@PathVariable(name = "id") Long id) {
        Task task = taskService.getTaskByID(id);
        taskService.updateTaskStatus(task, TaskStatus.COMPLETED);

        return "redirect:/";
    }

    // This method deletes (marks the task as non-active)
    // I have created a new enum called inactive to marked deleted tasks as inactive instead of delete them directly
    // This means I will need to filter out inactive from my find-all method in my repository
    @PostMapping("/tasks/{id}/delete")
    public String deleteTask (@PathVariable(name = "id") Long id) {
        Task task = taskService.getTaskByID(id);
        taskService.updateTaskStatus(task, TaskStatus.INACTIVE);

        return "redirect:/";
    }

}
