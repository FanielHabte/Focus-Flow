package com.fanihabte.focus_flow.enums;

public enum TaskStatus {
    PENDING, IN_PROGRESS, COMPLETED, OVERDUE, INACTIVE;

    @Override
    public String toString() {
        String status = name().replace("_", " ").toLowerCase();

        return status.substring(0,1).toUpperCase() + status.substring(1);
    }

    public String className() {
        return name().replace("_", "-").toLowerCase();
    }

}
