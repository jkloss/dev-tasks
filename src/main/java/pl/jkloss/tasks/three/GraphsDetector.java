package pl.jkloss.tasks.three;

import one.util.streamex.EntryStream;
import one.util.streamex.StreamEx;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class GraphsDetector {

    private static final long MINIMAL_GRAPH_NUMBER = 1L;

    private final List<Entry<Integer, Integer>> pairs;
    private final Set<Integer> connectedVertices;

    public GraphsDetector(List<Entry<Integer, Integer>> pairs) {
        this.pairs = pairs;
        this.connectedVertices = getConnectedVertices(pairs);
    }

    public long getNumberOfDetectedGraphs() {
        long numberOfPairsAfterGraphsSearch =
                StreamEx.of(pairs)
                        .remove(entry ->
                                CollectionUtils
                                        .containsAny(connectedVertices, entry.getKey(), entry.getValue()))
                        .count();
        if (((long) pairs.size()) == numberOfPairsAfterGraphsSearch) {
            return numberOfPairsAfterGraphsSearch;
        }
        return MINIMAL_GRAPH_NUMBER + numberOfPairsAfterGraphsSearch;
    }

    private Set<Integer> getConnectedVertices(List<Entry<Integer, Integer>> sourcePairs) {
        return EntryStream.ofPairs(sourcePairs)
                .flatMapKeyValue(this::flatPossiblePairs)
                .filter(entry ->
                        entry.getKey().equals(entry.getValue()))
                .map(Entry::getKey)
                .toSet();
    }

    private StreamEx<Entry<Integer, Integer>> flatPossiblePairs(Entry<Integer, Integer> first,
                                                                Entry<Integer, Integer> second) {
        return EntryStream.ofPairs(getEntriesContentAsList(first, second))
                .mapKeyValue(Map::entry);
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
