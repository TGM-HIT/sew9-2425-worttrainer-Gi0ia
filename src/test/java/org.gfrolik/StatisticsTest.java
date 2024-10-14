package org.gfrolik;

import static org.junit.jupiter.api.Assertions.*; // JUnit 5

import org.junit.jupiter.api.Test;

public class StatisticsTest {

    @Test
    public void testToString() {
        Statistics stats = new Statistics();
        stats.incrementCorrectAttempts();
        stats.incrementWrongAttempts();
        stats.incrementWrongAttempts();
        String expected = "Total Attempts: 3\nCorrect Attempts: 1\nWrong Attempts: 2";
        assertEquals(expected, stats.getStatisticsAsString());
    }
}
