package utils;

public class StringUtils {

    private StringUtils() {
    }

    public static String deleteAllExceptNumbers(String string) {
        return string.replaceAll("[^0-9]", "");
    }
}
