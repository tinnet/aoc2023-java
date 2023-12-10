package net.coronam;

import net.coronam.y2023.day09.Day09;

import static net.coronam.Utils.println;
import static net.coronam.Utils.readInput;

public class Main {
    public static void main(String[] args) {
        var day = new Day09();

        var lines = readInput("Day09");
        println(day.part1(lines));

        var lines2 = readInput("Day09");
        println(day.part2(lines2));
    }
}