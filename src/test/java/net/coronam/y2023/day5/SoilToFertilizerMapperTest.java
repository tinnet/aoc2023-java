package net.coronam.y2023.day5;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SoilToFertilizerMapperTest {

    private static List<String> input;

    @BeforeAll
    static void beforeAll() {
        input =
                """
                52 50 48

                soil-to-fertilizer map:
                0 15 37
                37 52 2
                39 0 15

                fertilizer-to-water map:
                49 53 8
                0 11 42
                42 0 75"""
                        .lines()
                        .toList();
    }

    @ParameterizedTest
    @MethodSource("testExamplesSource")
    void testExamples(Resources.Soil soil, Resources.Fertilizer fertilizer) {
        var s2fm = SoilToFertilizerMapper.parse(input);
        assertThat(s2fm.getDestination(soil)).isEqualTo(fertilizer);
    }

    static Stream<Arguments> testExamplesSource() {
        /*
         *     Seed 79, soil 81, fertilizer 81, water 81, light 74, temperature 78, humidity 78, location 82.
         *     Seed 14, soil 14, fertilizer 53, water 49, light 42, temperature 42, humidity 43, location 43.
         *     Seed 55, soil 57, fertilizer 57, water 53, light 46, temperature 82, humidity 82, location 86.
         *     Seed 13, soil 13, fertilizer 52, water 41, light 34, temperature 34, humidity 35, location 35.
         */
        return Stream.of(
                Arguments.arguments(new Resources.Soil(81), new Resources.Fertilizer(81)),
                Arguments.arguments(new Resources.Soil(14), new Resources.Fertilizer(53)),
                Arguments.arguments(new Resources.Soil(57), new Resources.Fertilizer(57)),
                Arguments.arguments(new Resources.Soil(13), new Resources.Fertilizer(52)));
    }
}