package com.fanihabte.focus_flow.repository;

import com.fanihabte.focus_flow.entity.Task;
import com.fanihabte.focus_flow.enums.TaskStatus;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @NullMarked
    List<Task> findAllByStatusNot(TaskStatus status);

    @NullMarked @Override
    Optional<Task> findById(Long aLong);

    @NullMarked
    List<Task> findByStatus(TaskStatus taskStatus);

    @NullMarked
    List<Task> findByDueDateBetweenAndStatusNot (LocalDateTime startDateTime, LocalDateTime endDateTime, TaskStatus taskStatus);

    @Override @NullMarked
    <S extends Task> S save(S entity);


}
