package net.coronam.y2023.day05;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.Map;
import java.util.SequencedCollection;
import java.util.stream.Collectors;

/*
 * soil-to-fertilizer map:
 * 0 15 37
 * 37 52 2
 * 39 0 15
 */
public class SoilToFertilizerMapper implements BaseMap<Resources.Soil, Resources.Fertilizer> {
    private final BiMap<Resources.Soil, Resources.Fertilizer> soilFertilizerBiMap;

    public SoilToFertilizerMapper(Map<Resources.Soil, Resources.Fertilizer> soilFertilizerMap) {
        this.soilFertilizerBiMap = HashBiMap.create(soilFertilizerMap);
    }

    @Override
    public Resources.Fertilizer getDestination(Resources.Soil soil) {
        return this.soilFertilizerBiMap.getOrDefault(soil, new Resources.Fertilizer(soil.id()));
    }

    public static SoilToFertilizerMapper parse(SequencedCollection<String> lines) {
        var s2sBlock = BaseMap.findBlock("soil-to-fertilizer map:", lines);
        var mapped = s2sBlock.stream()
                .flatMap(BaseMap::parseLine)
                .collect(Collectors.toMap(
                        (m) -> new Resources.Soil(m.source()), (m) -> new Resources.Fertilizer(m.destination())));

        return new SoilToFertilizerMapper(mapped);
    }
}
