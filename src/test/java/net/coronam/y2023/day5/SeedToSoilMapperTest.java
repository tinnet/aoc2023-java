package net.coronam.y2023.day5;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SeedToSoilMapperTest {

    private static List<String> input;

    @BeforeAll
    static void beforeAll() {
        input =
                """
                seeds: 79 14 55 13

                seed-to-soil map:
                50 98 2
                52 50 48

                soil-to-fertilizer map:
                0 15 37
                37 52 2
                39 0 15"""
                        .lines()
                        .toList();
    }

    @ParameterizedTest
    @MethodSource("testExamplesSource")
    void testExamples(Resources.Seed seed, Resources.Soil soil) {
        var s2sm = SeedToSoilMapper.parse(input);
        assertThat(s2sm.getDestination(seed)).isEqualTo(soil);
    }

    static Stream<Arguments> testExamplesSource() {
        return Stream.of(
                Arguments.arguments(new Resources.Seed(79), new Resources.Soil(81)),
                Arguments.arguments(new Resources.Seed(14), new Resources.Soil(14)),
                Arguments.arguments(new Resources.Seed(55), new Resources.Soil(57)),
                Arguments.arguments(new Resources.Seed(13), new Resources.Soil(13)),
                Arguments.arguments(new Resources.Seed(0), new Resources.Soil(0)),
                Arguments.arguments(new Resources.Seed(1), new Resources.Soil(1)),
                Arguments.arguments(new Resources.Seed(48), new Resources.Soil(48)),
                Arguments.arguments(new Resources.Seed(49), new Resources.Soil(49)),
                Arguments.arguments(new Resources.Seed(50), new Resources.Soil(52)),
                Arguments.arguments(new Resources.Seed(51), new Resources.Soil(53)),
                Arguments.arguments(new Resources.Seed(96), new Resources.Soil(98)),
                Arguments.arguments(new Resources.Seed(98), new Resources.Soil(50)),
                Arguments.arguments(new Resources.Seed(99), new Resources.Soil(51)));
    }
}