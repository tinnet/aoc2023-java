package net.coronam.y2023.day09;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day09Test {

    List<String> histories;
    Day09 day;

    @BeforeEach
    void setUp() {
        histories = List.of("0 3 6 9 12 15", "1 3 6 10 15 21", "10 13 16 21 30 45");
        day = new Day09();
    }

    @Test
    void testCorrectSequences() {
        assertThat(day.sequences(List.of(0, 3, 6, 9, 12, 15)))
                .containsExactly(List.of(3, 3, 3, 3, 3), List.of(0, 0, 0, 0));

        assertThat(day.sequences(List.of(1, 3, 6, 10, 15, 21)))
                .containsExactly(List.of(2, 3, 4, 5, 6), List.of(1, 1, 1, 1), List.of(0, 0, 0));

        assertThat(day.sequences(List.of(10, 13, 16, 21, 30, 45)))
                .containsExactly(List.of(3, 3, 5, 9, 15), List.of(0, 2, 4, 6), List.of(2, 2, 2), List.of(0, 0));
    }

    @Test
    void testCorrectExtrapolateForward() {
        assertThat(day.extrapolateForward(List.of(0, 3, 6, 9, 12, 15))).isEqualTo(18);

        assertThat(day.extrapolateForward(List.of(1, 3, 6, 10, 15, 21))).isEqualTo(28);

        assertThat(day.extrapolateForward(List.of(10, 13, 16, 21, 30, 45))).isEqualTo(68);
    }

    @Test
    void testCorrectExtrapolateBackwards() {
        assertThat(day.extrapolateBackwards(List.of(0, 3, 6, 9, 12, 15))).isEqualTo(-3);

        assertThat(day.extrapolateBackwards(List.of(1, 3, 6, 10, 15, 21))).isEqualTo(0);

        assertThat(day.extrapolateBackwards(List.of(10, 13, 16, 21, 30, 45))).isEqualTo(5);
    }

    @Test
    void testCorrectPart1() {
        assertThat(day.part1(histories)).isEqualTo(114);
    }

    void testCorrectPart2() {
        assertThat(day.part2(histories)).isEqualTo(2);
    }
}