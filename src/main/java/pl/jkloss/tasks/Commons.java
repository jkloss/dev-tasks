package pl.jkloss.tasks;

import com.google.common.base.Splitter;
import one.util.streamex.StreamEx;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Scanner;

public final class Commons {

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

    private static Splitter configureSplitter() {
        return Splitter.on(StringUtils.SPACE)
                .trimResults()
                .omitEmptyStrings();
    }
}
