package org.gfrolik;

/**
 * MODEL LAYER
 * Flashcard object that is part of a flashcard deck
 * @author gioia
 * @version 2024-10-07
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

    // get attempts
    public int getAttempts() {
        return attempts;
    }

    // get correct
    public int getCorrectCount() {
        return correctCount;
    }

    // get wrong
    public int getWrongCount() {
        return wrongCount;
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
    public String getStatistics() {
        return String.format(
                "Total Attempts: %d, Correct Attempts: %d, Wrong Attempts: %d, Success Rate: %.2f%%",
                attempts, correctCount, wrongCount, getSuccessRate()
        );
    }
}
