package com.fanihabte.focus_flow.task;

import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Override @NullMarked
    List<Task> findAll();
    List<Task> findByStatus(TaskStatus taskStatus);
    List<Task> findByDueDateBetween (LocalDateTime startDateTime, LocalDateTime endDateTime);



}
