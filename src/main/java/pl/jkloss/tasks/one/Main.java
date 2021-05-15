package pl.jkloss.tasks.one;

import one.util.streamex.StreamEx;
import pl.jkloss.tasks.Commons;

import java.util.List;

final class Main {

    public static void main(String[] args) {
        System.out.println("Insert one-line, space separated, integer values (e.g. 1, 2, 3, 4)");
        List<Integer> values = Commons.loadInput2IntegerList();
        CustomStatistics customStatistics =
                StreamEx.of(values)
                        .distinct()
                        .sorted()
                        .toListAndThen(CustomStatistics::new);
        Commons.displayIntegerList(customStatistics.getResultList());
        System.out.println("count: " + values.size());
        System.out.println("distinct: " + customStatistics.getCount());
        System.out.println("min: " + customStatistics.getMin());
        System.out.println("max: " + customStatistics.getMax());
    }
}
