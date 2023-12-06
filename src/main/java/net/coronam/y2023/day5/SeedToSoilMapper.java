package net.coronam.y2023.day5;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.Map;
import java.util.SequencedCollection;
import java.util.stream.Collectors;

public class SeedToSoilMapper implements BaseMap<Resources.Seed, Resources.Soil> {

    private final BiMap<Resources.Seed, Resources.Soil> seedSoilBiMap;

    public SeedToSoilMapper(Map<Resources.Seed, Resources.Soil> seedSoilBiMap) {
        this.seedSoilBiMap = HashBiMap.create(seedSoilBiMap);
    }

    @Override
    public Resources.Soil getDestination(Resources.Seed seed) {
        return this.seedSoilBiMap.getOrDefault(seed, new Resources.Soil(seed.id()));
    }

    public static SeedToSoilMapper parse(SequencedCollection<String> lines) {
        var s2sBlock = BaseMap.findBlock("seed-to-soil map:", lines);

        var mapped = s2sBlock.stream()
                .flatMap(BaseMap::parseLine)
                .collect(Collectors.toMap(
                        (m) -> new Resources.Seed(m.source()), (m) -> new Resources.Soil(m.destination())));

        return new SeedToSoilMapper(mapped);
    }
}
