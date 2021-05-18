package pl.jkloss.tasks.two;

import org.apache.commons.lang3.StringUtils;
import pl.jkloss.tasks.Commons;

import java.util.List;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Insert one-line, space separated, integer values (e.g. 1 2 3 4)");
            List<Integer> inputAsList = Commons.loadInput2IntegerList(scanner);
            PairExtractingPolicy pairExtractingPolicy = new PairExtractingPolicy(inputAsList);
            pairExtractingPolicy
                    .findPairs()
                    .forEach(entry ->
                            System.out.println(entry.getKey() + StringUtils.SPACE + entry.getValue()));
        }
    }
}
