package com.fanihabte.focus_flow.focusSession;

import com.fanihabte.focus_flow.task.Task;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table (name = "focus_session")
public class FocusSession {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String sessionId;

    @ManyToOne
    @JoinColumn(name="task_id", nullable = false, foreignKey = @ForeignKey(name = "FK_SESSION_TASK"))
    private Task task;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int plannedDurationMinutes;
    private int actualDurationMinutes;

    @Enumerated(EnumType.STRING)
    private SessionStatus sessionStatus;

    public FocusSession () {

    }

    public String getSessionId() {
        return sessionId;
    }

    public Task getTask () {
        return task;
    }

    public void setTask (Task task) {
        this.task = task;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getPlannedDurationMinutes() {
        return plannedDurationMinutes;
    }

    public void setPlannedDurationMinutes(int plannedDurationMinutes) {
        this.plannedDurationMinutes = plannedDurationMinutes;
    }

    public int getActualDurationMinutes() {
        return actualDurationMinutes;
    }

    public void setActualDurationMinutes(int actualDurationMinutes) {
        this.actualDurationMinutes = actualDurationMinutes;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
    }
}
