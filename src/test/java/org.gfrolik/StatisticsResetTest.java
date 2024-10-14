package org.gfrolik;

import static org.junit.jupiter.api.Assertions.*; // JUnit 5

import org.junit.jupiter.api.Test;

public class StatisticsResetTest {

    @Test
    public void testResetStatistics() {
        Statistics stats = new Statistics();
        stats.incrementCorrectAttempts();
        stats.incrementWrongAttempts();
        stats.incrementWrongAttempts();
        stats.resetStatistics();  // Reset the statistics

        assertEquals(0, stats.getAttempts());
        assertEquals(0, stats.getCorrectCount());
        assertEquals(0, stats.getWrongCount());
    }
}
