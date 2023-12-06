package net.coronam.y2023;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static net.coronam.Utils.readInput;
import static org.assertj.core.api.Assertions.assertThat;

class Day05Test {

    List<String> lines;

    @BeforeEach
    void setUp() {
        lines = readInput("Day05_test");
    }

    @Test
    void testInputNotEmpty() {
        assertThat(lines).isNotEmpty();
    }

    @Test
    void testOutput() {
        var day5 = new Day05();
        assertThat(day5.part1(lines)).isEqualTo(35);
    }
}