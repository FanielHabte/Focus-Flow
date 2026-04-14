package com.fanihabte.focus_flow.task;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    TaskController (TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/numberOfOpenTasks")
    public Integer countOfOpenTasks () {
        return taskService.countOfOpenTasks();
    }

    @GetMapping("/all")
    public List<Task> getAllTasks () {
        return taskService.getAllTasks();
    }

    @GetMapping("/compeltionPercntage")
    public Double getCompletionPercentage () {
        return taskService.completionPercentage();
    }

}
