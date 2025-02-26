package org.example.thought;

import jakarta.persistence.*;

@Entity
public class Thought {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String automaticThought;
    private String currentSituation;

    @Enumerated(EnumType.STRING)
    private ThinkingErrorTypes thinkingErrorType = ThinkingErrorTypes.NONE;

    private String timeOfDay;
    private String mood;
    private int moodIntensity;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAutomaticThought() {
        return automaticThought;
    }

    public void setAutomaticThought(String automaticThought) {
        this.automaticThought = automaticThought;
    }

    public String getCurrentSituation() {
        return currentSituation;
    }

    public void setCurrentSituation(String currentSituation) {
        this.currentSituation = currentSituation;
    }

    public ThinkingErrorTypes getThinkingErrorType() {
        return thinkingErrorType;
    }

    public void setThinkingErrorType(ThinkingErrorTypes thinkingErrorType) {
        this.thinkingErrorType = thinkingErrorType;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public int getMoodIntensity() {
        return moodIntensity;
    }

    public void setMoodIntensity(int moodIntensity) {
        this.moodIntensity = moodIntensity;
    }
}
