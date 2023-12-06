package net.coronam.y2023.day5;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.Map;

public class Mapper<S extends Resources.Source, D extends Resources.Destination> {

    private final BiMap<S, D> bimapping;

    public Mapper(Map<S, D> mapping) {
        bimapping = HashBiMap.create(mapping);
    }
}
