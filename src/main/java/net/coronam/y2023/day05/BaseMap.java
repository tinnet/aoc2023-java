package net.coronam.y2023.day05;

import java.util.List;
import java.util.SequencedCollection;
import java.util.stream.LongStream;
import java.util.stream.Stream;

interface BaseMap<S extends Resources.Source, D extends Resources.Destination> {

    record Mapping(long source, long destination) {}

    D getDestination(S source);

    static List<String> findBlock(String header, SequencedCollection<String> lines) {
        return lines.stream()
                .dropWhile(l -> !l.equals(header))
                .skip(1)
                .takeWhile(l -> !l.isEmpty())
                .toList();
    }

    static Stream<Mapping> parseLine(String line) {
        var chunks = line.split("\\s", 3);
        var destinationStart = Long.parseUnsignedLong(chunks[0]);
        var sourceStart = Long.parseUnsignedLong(chunks[1]);
        var rangeLength = Long.parseUnsignedLong(chunks[2]);

        return LongStream.range(0, rangeLength).mapToObj(i -> new Mapping(sourceStart + i, destinationStart + i));
    }
}
