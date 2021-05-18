package pl.jkloss.tasks.one;

import one.util.streamex.StreamEx;
import org.apache.commons.lang3.StringUtils;
import pl.jkloss.tasks.Commons;

import java.util.List;
import java.util.Scanner;

final class Main {

    private static final String LIST_REPLACEMENT_REGEX = "[\\[\\],]";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Insert one-line, space separated, integer values (e.g. 1 2 3 4)");
            List<Integer> values = Commons.loadInput2IntegerList(scanner);
            CustomStatistics customStatistics = getCustomStatistics(values);
            displayIntegerList(customStatistics.getResultList());
            System.out.println("count: " + values.size());
            System.out.println("distinct: " + customStatistics.getCount());
            System.out.println("min: " + customStatistics.getMin());
            System.out.println("max: " + customStatistics.getMax());
        }
    }

    private static CustomStatistics getCustomStatistics(List<Integer> values) {
        return StreamEx.of(values)
                .distinct()
                .sorted()
                .toListAndThen(CustomStatistics::new);
    }

    private static void displayIntegerList(List<Integer> list) {
        String listAsString = list.toString();
        String listToDisplay = listAsString.replaceAll(LIST_REPLACEMENT_REGEX, StringUtils.EMPTY);
        System.out.println(listToDisplay);
    }
}
