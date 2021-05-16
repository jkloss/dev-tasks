package pl.jkloss.tasks.three;

import one.util.streamex.EntryStream;

import java.util.List;
import java.util.Map.Entry;

class GraphsDetector {

    private final List<Entry<Integer, Integer>> pairs;

    public GraphsDetector(List<Entry<Integer, Integer>> pairs) {
        this.pairs = pairs;
    }

    public long getNumberOfDetectedGraphs() {
        return EntryStream.ofPairs(pairs)
                .removeKeyValue(this::anyConnected)
                .count();
    }

    private boolean anyConnected(Entry<Integer, Integer> first,
                                 Entry<Integer, Integer> second) {
        return EntryStream.ofPairs(getEntriesContentAsList(first, second))
                .anyMatch(Integer::equals);
    }

    private List<Integer> getEntriesContentAsList(Entry<Integer, Integer> first,
                                                  Entry<Integer, Integer> second) {
        return List.of(
                first.getKey(),
                first.getValue(),
                second.getKey(),
                second.getValue());
    }
}
