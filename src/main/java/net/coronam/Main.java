package net.coronam;

import net.coronam.y2023.day06.Day06;

import static net.coronam.Utils.println;
import static net.coronam.Utils.readInput;

public class Main {
    public static void main(String[] args) {
        var day = new Day06();

        var lines = readInput("Day06");
        println(day.part1(lines));

        var lines2 = readInput("Day06_part2");
        println(day.part2(lines2));
    }
}