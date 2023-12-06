package net.coronam;

import net.coronam.y2023.day05.Day05;

import static net.coronam.Utils.println;
import static net.coronam.Utils.readInput;

public class Main {
    public static void main(String[] args) {

        var lines = readInput("Day05");
        var day = new Day05();
        println(day.part1(lines));
        println(day.part2(lines));
    }
}