package net.coronam.y2023.day05;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.Map;
import java.util.SequencedCollection;
import java.util.stream.Collectors;

public class FertilizerToWaterMapper implements BaseMap<Resources.Fertilizer, Resources.Water> {

    private final BiMap<Resources.Fertilizer, Resources.Water> fertilizerWaterBiMap;

    public FertilizerToWaterMapper(Map<Resources.Fertilizer, Resources.Water> fertilizerWaterMap) {
        this.fertilizerWaterBiMap = HashBiMap.create(fertilizerWaterMap);
    }

    @Override
    public Resources.Water getDestination(Resources.Fertilizer source) {
        return this.fertilizerWaterBiMap.getOrDefault(source, new Resources.Water(source.id()));
    }

    /**
     *  * fertilizer-to-water map:
     *  * 49 53 8
     *  * 0 11 42
     *  * 42 0 7
     *  * 57 7 4
     */
    public static FertilizerToWaterMapper parse(SequencedCollection<String> input) {
        var block = BaseMap.findBlock("fertilizer-to-water map:", input);
        var mapped = block.stream()
                .flatMap(BaseMap::parseLine)
                .collect(Collectors.toMap(
                        (m) -> new Resources.Fertilizer(m.source()), (m) -> new Resources.Water(m.destination())));
        return new FertilizerToWaterMapper(mapped);
    }
}
