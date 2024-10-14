package org.gfrolik;

/**
 * MODEL LAYER
 * Flashcard object that is part of a flashcard deck
 * @author gioia
 * @version 2024-10-14
 */
public class Statistics {
    private int attempts;
    private int correctCount;
    private int wrongCount;

    public Statistics() {
        this.attempts = 0;
        this.correctCount = 0;
        this.wrongCount = 0;
    }

    public Statistics(int total, int correct, int wrong) {
        this.attempts = total;
        this.correctCount = correct;
        this.wrongCount = wrong;
    }

    // increment attempt count
    public void incrementTotalAttempts() {
        this.attempts++;
    }

    // increment correct count
    public void incrementCorrectAttempts() {
        this.correctCount++;
        incrementTotalAttempts();
    }

    // increment wrong count
    public void incrementWrongAttempts() {
        this.wrongCount++;
        incrementTotalAttempts();
    }

    // getters
    public int getAttempts() {
        return attempts;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public int getWrongCount() {
        return wrongCount;
    }

    // setters
    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public void setCorrectCount(int correctCount) {
        this.correctCount = correctCount;
    }

    public void setWrongCount(int wrongCount) {
        this.wrongCount = wrongCount;
    }

    // calculate success rate
    public double getSuccessRate() {
        if (attempts == 0) {
            return 0.0;
        }
        return (double) correctCount / attempts * 100;
    }

    // reset stats
    public void resetStatistics() {
        this.attempts = 0;
        this.correctCount = 0;
        this.wrongCount = 0;
    }

    // return stats as String
    public String getStatisticsAsString() {
        return String.format(
                "Total Attempts: %d\nCorrect Attempts: %d\nWrong Attempts: %d\nSuccess Rate: %.2f%%",
                attempts, correctCount, wrongCount, getSuccessRate()
        );
    }
}
