package utils;

public class StringUtils {

    private StringUtils() {
    }

    public static String deleteAllExceptNumbers(String string) {
        return string.replaceAll("[^0-9]", "");
    }

    public static String replaceSymbolBySpace(String oldString) {
        String newDate = "";

        if (oldString.contains(".")) {
            newDate = oldString.replaceAll("[.]", " ");
        } else if (oldString.contains("-")) {
            newDate = oldString.replaceAll("[-]", " ");
        } else if (oldString.contains("/")) {
            newDate = oldString.replaceAll("[/]", " ");
        }

        return newDate;
    }
}
