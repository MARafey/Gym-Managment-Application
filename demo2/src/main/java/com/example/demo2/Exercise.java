package com.example.demo2;

public class Exercise {
    public int exerciseID;
    public String exerciseName;
    public String description;
    public String difficulty;

    public Exercise(int exerciseID, String exerciseName, String description, String difficulty) {
        this.exerciseID = exerciseID;
        this.exerciseName = exerciseName;
        this.description = description;
        this.difficulty = difficulty;
    }

    public int getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(int exerciseID) {
        this.exerciseID = exerciseID;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        exerciseName = exerciseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        description = description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        difficulty = difficulty;
    }
}
