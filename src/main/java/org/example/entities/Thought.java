package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

//    A Tracked Thought Has
//*
// Id
// Time Of Day
// Current Situation
// Mood
// Mood Intensity
// Type Of Thinking Error
//
//
//
//
//
// *//

@Entity
public class Thought {


    @Id
    @SequenceGenerator(name = "thought_sequence", sequenceName = "thought_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "thought_sequence")
    private long id;

    private String automaticThought;
    private String currentSituation;
    private String thinkingErrorType;
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

    public String getThinkingErrorType() {
        return thinkingErrorType;
    }

    public void setThinkingErrorType(String thinkingErrorType) {
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
