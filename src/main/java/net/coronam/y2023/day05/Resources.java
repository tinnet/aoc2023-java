package net.coronam.y2023.day05;

public class Resources {
    interface Source {
        long id();
    }

    interface Destination {
        long id();
    }

    public record Seed(long id) implements Source {}

    public record Soil(long id) implements Source, Destination {}

    public record Fertilizer(long id) implements Source, Destination {}

    public record Water(long id) implements Source, Destination {}

    public record Light(long id) implements Source, Destination {}

    public record Temperature(long id) implements Source, Destination {}

    public record Humidty(long id) implements Source, Destination {}

    public record Location(long id) implements Destination {}
}
