package net.sourcewalker.xpclock;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class XpClockTest {

    private static final double EPSILON = 0.00001;
    private Calendar calendar;
    private XpClock clock;

    @Before
    public void setUp() {
        calendar = Calendar.getInstance();
        calendar.set(0, 0, 0, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        clock = new XpClock(calendar);
    }

    @Test
    public void checkZeroFractions() {
        assertEquals(0, clock.getHourFraction(), EPSILON);
        assertEquals(0, clock.getMinuteFraction(), EPSILON);
        assertEquals(0, clock.getSecondFraction(), EPSILON);
    }

    @Test
    public void checkSecondFractions() {
        System.out.println("seconds");
        for (int i = 0; i < 60; i++) {
            double expectedSeconds = (double) i / 60;
            double expectedMinutes = expectedSeconds / 60;
            double expectedHours = expectedMinutes / 24;
            printExpected(i, expectedSeconds, expectedMinutes, expectedHours);
            calendar.set(Calendar.SECOND, i);
            clock.setTime(calendar);
            assertEquals(expectedSeconds, clock.getSecondFraction(), EPSILON);
            assertEquals(expectedMinutes, clock.getMinuteFraction(), EPSILON);
            assertEquals(expectedHours, clock.getHourFraction(), EPSILON);
        }
    }

    private void printExpected(int i, double expectedSeconds,
            double expectedMinutes, double expectedHours) {
        System.out.println(String.format("i: %d h: %f m: %f s: %f", i,
                expectedHours, expectedMinutes, expectedSeconds));
    }

    @Test
    public void checkMinuteFractions() {
        System.out.println("minutes");
        double expectedSeconds = 0;
        for (int i = 0; i < 60; i++) {
            double expectedMinutes = (double) i / 60;
            double expectedHours = expectedMinutes / 24;
            printExpected(i, expectedSeconds, expectedMinutes, expectedHours);
            calendar.set(Calendar.MINUTE, i);
            clock.setTime(calendar);
            assertEquals(expectedSeconds, clock.getSecondFraction(), EPSILON);
            assertEquals(expectedMinutes, clock.getMinuteFraction(), EPSILON);
            assertEquals(expectedHours, clock.getHourFraction(), EPSILON);
        }
    }

    @Test
    public void checkHourFractions() {
        System.out.println("hours");
        double expectedSeconds = 0;
        double expectedMinutes = 0;
        for (int i = 0; i < 24; i++) {
            double expectedHours = (double) i / 24;
            printExpected(i, expectedSeconds, expectedMinutes, expectedHours);
            calendar.set(Calendar.HOUR_OF_DAY, i);
            clock.setTime(calendar);
            assertEquals(expectedSeconds, clock.getSecondFraction(), EPSILON);
            assertEquals(expectedMinutes, clock.getMinuteFraction(), EPSILON);
            assertEquals(expectedHours, clock.getHourFraction(), EPSILON);
        }
    }

}
