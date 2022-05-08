package utils;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListUtils {

    private static List<Double> newList;

    private ListUtils() {
    }

    public static <T, U> List<U> transform(List<T> list, Function<T, U> function) {
        return list.stream()
                .map(function)
                .collect(Collectors.toList());
    }

    public static String smallestValue(List<String> list) {
        newList = transform(list, Double::parseDouble);
        Collections.sort(newList);
        return String.valueOf(newList.get(0));
    }

    public static String biggestValue(List<String> list) {
        newList = transform(list, Double::parseDouble);
        Collections.sort(newList);
        Collections.reverse(newList);
        return String.valueOf(newList.get(0));
    }
}
