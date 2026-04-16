package com.fanihabte.focus_flow.service;

import com.fanihabte.focus_flow.entity.Task;
import com.fanihabte.focus_flow.enums.TaskStatus;
import com.fanihabte.focus_flow.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    private Integer[] getCompleteAndAllTaskCounts () {
        List<Task> allTasks = taskRepository.findAllByStatusNot(TaskStatus.INACTIVE);
        int countOfAllTasks = allTasks.size();
        int countOfCompletedTasks = allTasks
                .stream()
                .filter(task -> TaskStatus.COMPLETED.equals(task.getStatus()))
                .toList().size();
        return new Integer[] {countOfAllTasks, countOfCompletedTasks};
    }

    public Integer countOfOpenTasks () {
        Integer[] allAndCompleteTaskCounts = getCompleteAndAllTaskCounts();
        int countOfAllTasks = allAndCompleteTaskCounts[0];
        int countOfCompletedTasks = allAndCompleteTaskCounts[1];

        return countOfAllTasks - countOfCompletedTasks;
    }

    public String completionPercentage () {
        Integer[] allAndCompleteTaskCounts = getCompleteAndAllTaskCounts();
        int countOfAllTasks = allAndCompleteTaskCounts[0];
        int countOfCompletedTasks = allAndCompleteTaskCounts[1];

        if (countOfAllTasks == 0) {
            return "0.0%";
        }
        else {
            double percentage = ( (double)countOfCompletedTasks / (double)countOfAllTasks ) * 100;
            return String.format("%.1f", percentage) + "%" ;
        }
    }

    public List<Task> getAllTasks () {
        return taskRepository.findAllByStatusNot(TaskStatus.INACTIVE);
    }

    public List<Task> getCompletedTasks () {
        return taskRepository.findByStatus(TaskStatus.COMPLETED);
    }

    public List<Task> getCurrentDateTask () {
        LocalDateTime start = LocalDate.now().atTime(LocalTime.MIN);
        LocalDateTime end = LocalDate.now().atTime(LocalTime.MAX);

        return taskRepository.findByDueDateBetweenAndStatusNot(start, end, TaskStatus.INACTIVE);
    }

    public Optional<Task> getNearestCurrentDateTask () {
        return getCurrentDateTask()
                .stream().min(Comparator.comparing(Task::getDueDate));
    }

    public Task getTaskByID (Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found with ID: " + id));
    }

    public void createNewTask (Task newTask) {
        taskRepository.save(newTask);
    }

    public void updateTaskStatus(Task task, TaskStatus toStatus) {
        task.setStatus(toStatus);
        taskRepository.save(task);
    }

}
