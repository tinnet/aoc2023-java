package net.coronam.y2023.day06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static net.coronam.Utils.readInput;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BoatRacingTest {

    private List<String> lines;
    private BoatRacing boatRacing;

    @BeforeEach
    void setUp() {
        lines = readInput("Day06_test");
        boatRacing = new BoatRacing();
    }

    @ParameterizedTest
    @MethodSource("testTheMathSource")
    void testTheMath(int timePressed, int timeTotal, int expectedDistance) {
        assertThat(boatRacing.distanceTraveled(timePressed, timeTotal)).isEqualTo(expectedDistance);
    }

    /**
     *  So, because the first race lasts 7 milliseconds, you only have a few options:
     * <p>
     *  Don't hold the button at all (that is, hold it for 0 milliseconds) at the start of the race. The boat won't move; it will have traveled 0 millimeters by the end of the race.
     *  Hold the button for 1 millisecond at the start of the race. Then, the boat will travel at a speed of 1 millimeter per millisecond for 6 milliseconds, reaching a total distance traveled of 6 millimeters.
     *  Hold the button for 2 milliseconds, giving the boat a speed of 2 millimeters per millisecond. It will then get 5 milliseconds to move, reaching a total distance of 10 millimeters.
     *  Hold the button for 3 milliseconds. After its remaining 4 milliseconds of travel time, the boat will have gone 12 millimeters.
     *  Hold the button for 4 milliseconds. After its remaining 3 milliseconds of travel time, the boat will have gone 12 millimeters.
     *  Hold the button for 5 milliseconds, causing the boat to travel a total of 10 millimeters.
     *  Hold the button for 6 milliseconds, causing the boat to travel a total of 6 millimeters.
     *  Hold the button for 7 milliseconds. That's the entire duration of the race. You never let go of the button. The boat can't move until you let go of the button. Please make sure you let go of the button so the boat gets to move. 0 millimeters.
     */
    static Stream<Arguments> testTheMathSource() {
        return Stream.of(
                arguments(0, 7, 0),
                arguments(1, 7, 6),
                arguments(2, 7, 10),
                arguments(3, 7, 12),
                arguments(4, 7, 12),
                arguments(5, 7, 10),
                arguments(6, 7, 6),
                arguments(7, 7, 0));
    }

    @Test
    void testParse() {
        assertThat(boatRacing.parse(lines))
                .containsExactlyInAnyOrder(
                        new BoatRacing.Race(7, 9), new BoatRacing.Race(15, 40), new BoatRacing.Race(30, 200));
    }
}