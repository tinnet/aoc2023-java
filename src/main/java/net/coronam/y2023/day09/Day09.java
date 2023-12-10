package net.coronam.y2023.day09;

import com.google.common.collect.Lists;
import net.coronam.Solvable;
import one.util.streamex.StreamEx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Day09 implements Solvable<Integer> {
    @Override
    public Integer part1(List<String> input) {
        return getInputStream(input)
                .map(this::extrapolateForward)
                .reduce(Integer::sum)
                .orElseThrow();
    }

    @Override
    public Integer part2(List<String> input) {
        return getInputStream(input)
                .map(this::extrapolateBackwards)
                .reduce(Integer::sum)
                .orElseThrow();
    }

    private static Stream<List<Integer>> getInputStream(List<String> input) {
        return input.stream()
                .map(line -> line.split("\\s+"))
                .map(Arrays::asList)
                .map(l -> l.stream().map(Integer::valueOf).toList());
    }

    public List<List<Integer>> sequences(List<Integer> history) {
        var sequences = new ArrayList<List<Integer>>();
        sequences.add(sequenceDiff(history));
        while (sequences.getLast().stream().anyMatch(e -> e != 0)) {
            sequences.add(sequenceDiff(sequences.getLast()));
        }
        return sequences;
    }

    private List<Integer> sequenceDiff(List<Integer> sequence) {
        return StreamEx.of(sequence).pairMap((a, b) -> b - a).toList();
    }

    public Integer extrapolateForward(List<Integer> history) {
        var extraGrid = new ArrayList<List<Integer>>();
        sequences(history).reversed().forEach(s -> extraGrid.add(Lists.newArrayList(s)));
        extraGrid.add(Lists.newArrayList(history));

        extraGrid.getFirst().add(0);
        for (int i = 1; i < extraGrid.size(); i++) {
            var newNum = extraGrid.get(i).getLast() + extraGrid.get(i - 1).getLast();
            extraGrid.get(i).add(newNum);
        }

        return extraGrid.getLast().getLast();
    }

    public Integer extrapolateBackwards(List<Integer> history) {
        var extraGrid = new ArrayList<List<Integer>>();
        sequences(history).reversed().forEach(s -> extraGrid.add(Lists.newArrayList(s)));
        extraGrid.add(Lists.newArrayList(history));

        extraGrid.getFirst().addFirst(0);
        for (int i = 1; i < extraGrid.size(); i++) {
            var newNum = extraGrid.get(i).getFirst() - extraGrid.get(i - 1).getFirst();
            extraGrid.get(i).addFirst(newNum);
        }

        return extraGrid.getLast().getFirst();
    }
    /**
     * 5  10  13  16  21  30  45
     *   5   3   3   5   9  15
     *    -2   0   2   4   6
     *       2   2   2   2
     *         0   0   0
     */
}
