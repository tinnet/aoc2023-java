package net.coronam;

import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Utils {
    private Utils() {
    }

    public static void println(Collection<String> lines) {
        lines.forEach(System.out::println);
    }

    public static void println(Object obj) {
        System.out.println(obj);
    }

    public static List<String> readInput(String name) {
        try {
            var res = Resources.getResource(name + ".txt");
            return Files.readAllLines(Paths.get(res.toURI()));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Stream<List<T>> sliding(List<T> list, int size) {
        if (size > list.size()) {
            return Stream.of(list);
        }
        return IntStream
                .range(0, list.size()-size+1)
                .mapToObj(start -> list.subList(start, start+size));
    }

    public static Stream<String> stringStream(String input) {
        return input.codePoints()
                .mapToObj(c -> String.valueOf((char) c));

    }

    public static Stream<Character> charStream(String input) {
        return input.codePoints()
                .mapToObj(c -> (char) c);
    }
}
