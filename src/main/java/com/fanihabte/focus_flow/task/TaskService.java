package com.fanihabte.focus_flow.task;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Integer[] getCompleteAndAllTaskCounts () {
        List<Task> allTasks = taskRepository.findAll();
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

    public Double completionPercentage () {
        Integer[] allAndCompleteTaskCounts = getCompleteAndAllTaskCounts();
        int countOfAllTasks = allAndCompleteTaskCounts[0];
        int countOfCompletedTasks = allAndCompleteTaskCounts[1];

        if (countOfAllTasks == 0) {
            return 0.0;
        }
        else {
            return ( (double)countOfCompletedTasks / (double)countOfAllTasks ) * 100;
        }
    }

    public List<Task> getAllTasks () {
        return taskRepository.findAll();
    }

    public List<Task> getCompletedTasks () {
        return taskRepository.findByStatus(TaskStatus.COMPLETED);
    }

    public List<Task> getCurrentDateTask () {
        LocalDateTime start = LocalDate.now().atTime(LocalTime.MIN);
        LocalDateTime end = LocalDate.now().atTime(LocalTime.MAX);

        return taskRepository.findByDueDateBetween(start, end);
    }

    public Task getNearestCurrentDateTask () {
        List<Task> orderedTasks = getCurrentDateTask()
                .stream()
                .sorted(Comparator.comparing(Task::getDueDate))
                .toList();

        return orderedTasks.getFirst();
    }

}
