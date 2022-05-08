package utils;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListUtils {

    private ListUtils() {}

    public static <T, U> List<U> transform(List<T> list, Function<T, U> function)
    {
        return list.stream()
                .map(function)
                .collect(Collectors.toList());
    }

    public static String smallestValue(List<String> list) {
        Collections.sort(transform(list, Double::parseDouble));
        return list.get(0);
    }

    public static String biggestValue(List<String> list) {
        Collections.reverse(transform(list, Double::parseDouble));
        return list.get(0);
    }
}
