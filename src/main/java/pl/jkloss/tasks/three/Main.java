package pl.jkloss.tasks.three;

import pl.jkloss.tasks.Commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner scanner = Commons.getScanner();
        System.out.println("Insert number of lines that will represent " +
                "a connection between two vertices in a graph");
        int numberOfLines = Integer.parseInt(scanner.nextLine());
        List<Element> pairs = new ArrayList<>();
        for (int i = 0; i < numberOfLines; i++) {
            System.out.println("Insert one-line, space separated pair of two vertices in a graph");
            List<Integer> inputAsList = Commons.loadInput2IntegerList();
            Element element = mapInput2Entry(inputAsList);
            pairs.add(element);
        }
        GraphsDetector graphsDetector = new GraphsDetector(pairs);
        System.out.println(graphsDetector.getNumberOfDetectedGraphs());
    }

    private static Element mapInput2Entry(List<Integer> inputAsList) {
        if (inputAsList.size() != 2) {
            throw new RuntimeException("Too many numbers given as input. Only pairs allowed");
        }
        int first = inputAsList.get(0);
        int second = inputAsList.get(1);
        return new Element(first, second);
    }
}
