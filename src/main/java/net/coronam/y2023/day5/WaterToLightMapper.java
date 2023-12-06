package net.coronam.y2023.day5;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.Map;
import java.util.SequencedCollection;
import java.util.stream.Collectors;

public class WaterToLightMapper implements BaseMap<Resources.Water, Resources.Light> {

    private final BiMap<Resources.Water, Resources.Light> mapping;

    public WaterToLightMapper(Map<Resources.Water, Resources.Light> mapping) {
        this.mapping = HashBiMap.create(mapping);
    }

    @Override
    public Resources.Light getDestination(Resources.Water source) {
        return mapping.computeIfAbsent(source, (s) -> new Resources.Light(s.id()));
    }

    public static WaterToLightMapper parse(SequencedCollection<String> input) {
        var block = BaseMap.findBlock("water-to-light map:", input);
        var mapped = block.stream()
                .flatMap(BaseMap::parseLine)
                .collect(Collectors.toMap(
                        (m) -> new Resources.Water(m.source()), (m) -> new Resources.Light(m.destination())));
        return new WaterToLightMapper(mapped);
    }
}
