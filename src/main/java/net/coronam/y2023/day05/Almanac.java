package net.coronam.y2023.day05;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class Almanac {

    public final Collection<Resources.Seed> seeds;
    private final SeedToSoilMapper seedToSoilMapper;

    private final SoilToFertilizerMapper soilToFertilizerMapper;

    private final FertilizerToWaterMapper fertilizerToWaterMapper;

    private final WaterToLightMapper waterToLightMapper;
    private final LightToTemperatureMapper lightToTemperatureMapper;
    private final TemperatureToHumidtyMapper temperatureToHumidtyMapper;

    private final HumidtyToLocationMapper humidtyToLocationMapper;

    public Almanac(
            Collection<Resources.Seed> seeds,
            SeedToSoilMapper seedToSoilMapper,
            SoilToFertilizerMapper soilToFertilizerMapper,
            FertilizerToWaterMapper fertilizerToWaterMapper,
            WaterToLightMapper waterToLightMapper,
            LightToTemperatureMapper lightToTemperatureMapper,
            TemperatureToHumidtyMapper temperatureToHumidtyMapper,
            HumidtyToLocationMapper humidtyToLocationMapper) {
        this.seeds = seeds;
        this.seedToSoilMapper = seedToSoilMapper;
        this.soilToFertilizerMapper = soilToFertilizerMapper;
        this.fertilizerToWaterMapper = fertilizerToWaterMapper;
        this.waterToLightMapper = waterToLightMapper;
        this.lightToTemperatureMapper = lightToTemperatureMapper;
        this.temperatureToHumidtyMapper = temperatureToHumidtyMapper;
        this.humidtyToLocationMapper = humidtyToLocationMapper;
    }

    public static Almanac parse(List<String> input) {
        var seeds = parseSeeds(input.getFirst());
        var seed2Soil = SeedToSoilMapper.parse(input);
        var soil2Fert = SoilToFertilizerMapper.parse(input);
        var fert2water = FertilizerToWaterMapper.parse(input);
        var water2light = WaterToLightMapper.parse(input);
        var light2temp = LightToTemperatureMapper.parse(input);
        var temp2humid = TemperatureToHumidtyMapper.parse(input);
        var humid2location = HumidtyToLocationMapper.parse(input);

        return new Almanac(
                seeds, seed2Soil, soil2Fert, fert2water, water2light, light2temp, temp2humid, humid2location);
    }

    private static Collection<Resources.Seed> parseSeeds(String seedString) {
        var seedIds = StringUtils.removeStart(seedString, "seeds: ").split("\\s");
        return Arrays.stream(seedIds)
                .map(Integer::parseUnsignedInt)
                .map(Resources.Seed::new)
                .toList();
    }

    public Resources.Location getLocation(Resources.Seed seed) {
        return Stream.of(seed)
                .map(seedToSoilMapper::getDestination)
                .map(soilToFertilizerMapper::getDestination)
                .map(fertilizerToWaterMapper::getDestination)
                .map(waterToLightMapper::getDestination)
                .map(lightToTemperatureMapper::getDestination)
                .map(temperatureToHumidtyMapper::getDestination)
                .map(humidtyToLocationMapper::getDestination)
                .findFirst()
                .orElseThrow();
    }
}
