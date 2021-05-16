package pl.jkloss.tasks.one;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import one.util.streamex.StreamEx;

import java.util.IntSummaryStatistics;
import java.util.List;

@Getter
@EqualsAndHashCode
@ToString
class CustomStatistics {

    private final int max;
    private final int min;
    private final long count;
    private final List<Integer> resultList;

    public CustomStatistics(List<Integer> resultList) {
        IntSummaryStatistics statistics = getStatistics(resultList);
        this.max = statistics.getMax();
        this.min = statistics.getMin();
        this.count = statistics.getCount();
        this.resultList = resultList;
    }

    private IntSummaryStatistics getStatistics(List<Integer> source) {
        return StreamEx.of(source)
                .mapToInt(Integer::intValue)
                .summaryStatistics();
    }
}
