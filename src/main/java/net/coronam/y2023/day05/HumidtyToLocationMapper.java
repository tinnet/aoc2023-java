package net.coronam.y2023.day05;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.Map;
import java.util.SequencedCollection;
import java.util.stream.Collectors;

public class HumidtyToLocationMapper implements BaseMap<Resources.Humidty, Resources.Location> {

    private final BiMap<Resources.Humidty, Resources.Location> mapping;

    public HumidtyToLocationMapper(Map<Resources.Humidty, Resources.Location> mapping) {
        this.mapping = HashBiMap.create(mapping);
    }

    @Override
    public Resources.Location getDestination(Resources.Humidty source) {
        return mapping.computeIfAbsent(source, (s) -> new Resources.Location(s.id()));
    }

    /**
     * humidity-to-location map:
     * 60 56 37
     * 56 93 4
     */
    public static HumidtyToLocationMapper parse(SequencedCollection<String> input) {
        var block = BaseMap.findBlock("humidity-to-location map:", input);
        var mapped = block.stream()
                .flatMap(BaseMap::parseLine)
                .collect(Collectors.toMap(
                        (m) -> new Resources.Humidty(m.source()), (m) -> new Resources.Location(m.destination())));
        return new HumidtyToLocationMapper(mapped);
    }
}
