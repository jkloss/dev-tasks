package pl.jkloss.tasks;

import com.google.common.base.Splitter;
import one.util.streamex.StreamEx;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Scanner;

import static java.util.Objects.requireNonNull;

public final class Commons {

    private static final String LIST_REPLACEMENT_REGEX = "[\\[\\],]";

    private static final Splitter splitter = configureSplitter();

    private Commons() {
    }

    public static List<Integer> loadInput2IntegerList() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        List<String> splitInputValues = splitter.splitToList(input);
        return StreamEx.of(splitInputValues)
                .map(Integer::parseInt)
                .toList();
    }

    public static void displayIntegerList(List<Integer> list) {
        requireNonNull(list, "list must not be null");
        String listAsString = list.toString();
        String listToDisplay = listAsString.replaceAll(LIST_REPLACEMENT_REGEX, StringUtils.EMPTY);
        System.out.println(listToDisplay);
    }

    private static Splitter configureSplitter() {
        return Splitter.on(StringUtils.SPACE)
                .trimResults()
                .omitEmptyStrings();
    }
}
