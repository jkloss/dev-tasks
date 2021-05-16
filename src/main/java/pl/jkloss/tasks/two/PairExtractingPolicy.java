package pl.jkloss.tasks.two;

import one.util.streamex.EntryStream;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class PairExtractingPolicy {

    private static final int EXPECTED_SUM = 13;

    private final List<Integer> integerList;

    public PairExtractingPolicy(List<Integer> integerList) {
        this.integerList = integerList;
    }

    public List<Entry<Integer, Integer>> findPairs() {
        return EntryStream.ofPairs(integerList)
                .filterKeyValue((key, value) ->
                        key + value == EXPECTED_SUM)
                .mapKeyValue((key, value) ->
                        key > value ? Map.entry(value, key) : Map.entry(key, value))
                .sorted(Entry.comparingByKey())
                .toList();
    }
}
