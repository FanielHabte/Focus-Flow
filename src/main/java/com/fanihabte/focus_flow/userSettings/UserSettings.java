package com.fanihabte.focus_flow.userSettings;

import jakarta.persistence.*;

@Entity
@Table (name = "user_settings")
public class UserSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long settingsId;
    private int pomodoroDuration;
    private int shortBreakDuration;
    private int reminderLeadMinutes;
    private boolean musicEnabled;

    public UserSettings () {

    }

    public Long getSettingsId() {
        return settingsId;
    }

    public int getPomodoroDuration() {
        return pomodoroDuration;
    }

    public void setPomodoroDuration(int pomodoroDuration) {
        this.pomodoroDuration = pomodoroDuration;
    }

    public int getShortBreakDuration() {
        return shortBreakDuration;
    }

    public void setShortBreakDuration(int shortBreakDuration) {
        this.shortBreakDuration = shortBreakDuration;
    }

    public int getReminderLeadMinutes() {
        return reminderLeadMinutes;
    }

    public void setReminderLeadMinutes(int reminderLeadMinutes) {
        this.reminderLeadMinutes = reminderLeadMinutes;
    }

    public boolean isMusicEnabled() {
        return musicEnabled;
    }

    public void setMusicEnabled(boolean musicEnabled) {
        this.musicEnabled = musicEnabled;
    }
}
