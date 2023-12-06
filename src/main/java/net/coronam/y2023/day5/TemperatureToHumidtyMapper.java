package net.coronam.y2023.day5;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.Map;
import java.util.SequencedCollection;
import java.util.stream.Collectors;

public class TemperatureToHumidtyMapper implements BaseMap<Resources.Temperature, Resources.Humidty> {

    private final BiMap<Resources.Temperature, Resources.Humidty> mapping;

    public TemperatureToHumidtyMapper(Map<Resources.Temperature, Resources.Humidty> mapping) {
        this.mapping = HashBiMap.create(mapping);
    }

    @Override
    public Resources.Humidty getDestination(Resources.Temperature source) {
        return mapping.computeIfAbsent(source, (s) -> new Resources.Humidty(s.id()));
    }

    /**
     * temperature-to-humidity map:
     * 0 69 1
     * 1 0 69
     */
    public static TemperatureToHumidtyMapper parse(SequencedCollection<String> input) {
        var block = BaseMap.findBlock("temperature-to-humidity map:", input);
        var mapped = block.stream()
                .flatMap(BaseMap::parseLine)
                .collect(Collectors.toMap(
                        (m) -> new Resources.Temperature(m.source()), (m) -> new Resources.Humidty(m.destination())));
        return new TemperatureToHumidtyMapper(mapped);
    }
}
