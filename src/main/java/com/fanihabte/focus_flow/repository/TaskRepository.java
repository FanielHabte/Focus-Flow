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

    @Override @NullMarked
    List<Task> findAll();

    @Override @NullMarked
    Optional<Task> findById(Long aLong);

    List<Task> findByStatus(TaskStatus taskStatus);
    List<Task> findByDueDateBetween (LocalDateTime startDateTime, LocalDateTime endDateTime);

    @Override @NullMarked
    <S extends Task> S save(S entity);


}
