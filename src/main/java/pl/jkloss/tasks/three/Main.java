package pl.jkloss.tasks.three;

import one.util.streamex.StreamEx;
import pl.jkloss.tasks.Commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Insert number of pairs that will represent " +
                    "a connection between two vertices in a graph");
            int numberOfLines = Integer.parseInt(scanner.nextLine());
            validateInput(numberOfLines);
            List<Entry<Integer, Integer>> pairs = new ArrayList<>();
            for (int i = 0; i < numberOfLines; i++) {
                System.out.println("Insert one-line, space separated pair of vertices in a graph");
                List<Integer> inputAsList = Commons.loadInput2IntegerList(scanner);
                validateInput(inputAsList.toArray(Integer[]::new));
                Entry<Integer, Integer> element = mapInput2Entry(inputAsList);
                pairs.add(element);
            }
            GraphsCounter graphsCounter = new GraphsCounter(pairs);
            System.out.println(graphsCounter.count());
        }
    }

    private static Entry<Integer, Integer> mapInput2Entry(List<Integer> inputAsList) {
        if (inputAsList.size() != 2) {
            throw new IllegalStateException("Too many numbers given as input. Only pairs allowed");
        }
        int first = inputAsList.get(0);
        int second = inputAsList.get(1);
        return Map.entry(first, second);
    }

    private static void validateInput(Integer... input) {
        boolean anyNegative =
                StreamEx.of(input)
                        .anyMatch(value ->
                                value <= 0);
        if (anyNegative) {
            throw new IllegalStateException("Only positive values allowed!");
        }
    }
}
