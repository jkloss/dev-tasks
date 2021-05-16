package pl.jkloss.tasks;

import com.google.common.base.Splitter;
import one.util.streamex.StreamEx;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Scanner;

public final class Commons {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Splitter splitter = configureSplitter();

    private Commons() {
    }

    public static List<Integer> loadInput2IntegerList() {
        String input = scanner.nextLine();
        List<String> splitInputValues = splitter.splitToList(input);
        return StreamEx.of(splitInputValues)
                .map(Integer::parseInt)
                .toList();
    }

    public static Scanner getScanner() {
        return scanner;
    }

    private static Splitter configureSplitter() {
        return Splitter.on(StringUtils.SPACE)
                .trimResults()
                .omitEmptyStrings();
    }
}
