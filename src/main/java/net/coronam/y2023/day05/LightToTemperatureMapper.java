package net.coronam.y2023.day05;

import com.google.common.collect.HashBiMap;

import java.util.Map;
import java.util.SequencedCollection;
import java.util.stream.Collectors;

public class LightToTemperatureMapper implements BaseMap<Resources.Light, Resources.Temperature> {
    private final HashBiMap<Resources.Light, Resources.Temperature> mapping;

    public LightToTemperatureMapper(Map<Resources.Light, Resources.Temperature> lightTemperatureMap) {
        this.mapping = HashBiMap.create(lightTemperatureMap);
    }

    @Override
    public Resources.Temperature getDestination(Resources.Light source) {
        return this.mapping.computeIfAbsent(source, (s) -> new Resources.Temperature(s.id()));
    }

    /**
     * light-to-temperature map:
     * 45 77 23
     * 81 45 19
     * 68 64 13
     */
    public static LightToTemperatureMapper parse(SequencedCollection<String> input) {
        var block = BaseMap.findBlock("light-to-temperature map:", input);
        var mapped = block.stream()
                .flatMap(BaseMap::parseLine)
                .collect(Collectors.toMap(
                        (m) -> new Resources.Light(m.source()), (m) -> new Resources.Temperature(m.destination())));
        return new LightToTemperatureMapper(mapped);
    }
}
