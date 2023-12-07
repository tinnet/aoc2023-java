package net.coronam.y2023.day06;

import com.google.common.collect.Streams;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.SequencedCollection;
import java.util.stream.LongStream;

public class BoatRacing {
    public record Race(long time, long currentRecord) {}

    public long distanceTraveled(long timePressed, long timeTotal) {
        return timePressed * timeTotal - timePressed * timePressed;
    }

    public long countWaysToWin(Race race) {
        return LongStream.range(0, race.time)
                .filter(timePressed -> distanceTraveled(timePressed, race.time) > race.currentRecord)
                .count();
    }

    public SequencedCollection<Race> parse(SequencedCollection<String> lines) {
        if (lines.size() != 2) {
            throw new IllegalArgumentException("Invalid input format");
        }

        var times = parseTimes(lines.getFirst());
        var records = parseRecords(lines.getLast());

        return Streams.zip(times.stream(), records.stream(), Race::new).toList();
    }

    private SequencedCollection<Long> parseTimes(String line) {
        // Time:        40     92     97     90
        if (!line.startsWith("Time:")) {
            throw new IllegalArgumentException("Invalid line format");
        }

        var chunks = StringUtils.removeStart(line, "Time:").trim().split("\\s+");
        return Arrays.stream(chunks).map(Long::parseUnsignedLong).toList();
    }

    private SequencedCollection<Long> parseRecords(String line) {
        // Distance:   215   1064   1505   1100
        if (!line.startsWith("Distance:")) {
            throw new IllegalArgumentException("Invalid line format");
        }

        var chunks = StringUtils.removeStart(line, "Distance:").trim().split("\\s+");
        return Arrays.stream(chunks).map(Long::parseUnsignedLong).toList();
    }
}
