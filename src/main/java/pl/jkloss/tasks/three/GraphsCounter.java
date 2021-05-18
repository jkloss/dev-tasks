package pl.jkloss.tasks.three;

import one.util.streamex.StreamEx;

import java.util.*;
import java.util.Map.Entry;

class GraphsCounter {

    private final Map<Integer, Boolean> visited = new HashMap<>();
    private final Map<Integer, Set<Integer>> accumulator = new HashMap<>();

    private final List<Entry<Integer, Integer>> pairs;

    private GraphsCounter(List<Entry<Integer, Integer>> pairs) {
        this.pairs = pairs;
    }

    public static int countFor(List<Entry<Integer, Integer>> sourcePairs) {
        GraphsCounter graphsCounter = new GraphsCounter(sourcePairs);
        return graphsCounter.count();
    }

    private int count() {
        addEdges();
        int counter = 0;
        for (Entry<Integer, Boolean> entry : visited.entrySet()) {
            if (!entry.getValue()) {
                dfs(entry.getKey());
                counter++;
            }
        }
        return counter;
    }

    private void addEdges() {
        pairs.forEach(entry -> {
            Integer source = entry.getKey();
            Integer target = entry.getValue();
            accumulator.putIfAbsent(source, new HashSet<>());
            accumulator.putIfAbsent(target, new HashSet<>());
            accumulator.get(source).add(target);
            accumulator.get(source).add(source);
            accumulator.get(target).add(source);
            accumulator.get(target).add(target);
            visited.put(source, false);
            visited.put(target, false);
        });
    }

    private void dfs(int vertex) {
        visited.put(vertex, true);
        StreamEx.of(accumulator.get(vertex))
                .remove(visited::get)
                .forEach(this::dfs);
    }
}
