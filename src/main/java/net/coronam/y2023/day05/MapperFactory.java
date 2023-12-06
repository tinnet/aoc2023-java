package net.coronam.y2023.day05;

public class MapperFactory {

    //    public <S extends Almanac.Source, D extends Almanac.Destination> Mapper<S, D> getMapper(
    //            SequencedCollection<String> lines, String header) {
    //        var s2sBlock = BaseMap.findBlock(header, lines);
    //        var mapped =
    //                s2sBlock.stream().flatMap(this::parseLine).collect(Collectors.toMap(Pair::getLeft,
    // Pair::getRight));
    //
    //        return new Mapper<Almanac.Source, Almanac.Destination>(mapped);
    //    }
    //
    //    private <S extends Almanac.Source, D extends Almanac.Destination> Stream<Pair<S, D>> parseLine(String line) {
    //        var chunks = line.split("\\s", 3);
    //        var soilStart = Long.parseUnsignedLong(chunks[0]);
    //        var seedStart = Long.parseUnsignedLong(chunks[1]);
    //        var rangeLength = Long.parseUnsignedLong(chunks[2]);
    //
    //        return LongStream.range(0, rangeLength)
    //                .mapToObj(i -> Pair.of(new S(seedStart + i), new Almanac.Soil(soilStart + i)));
    //    }
}
