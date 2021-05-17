package pl.jkloss.tasks.three;

import one.util.streamex.StreamEx;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

class GraphsDetector {

    private final List<List<Element>> graphs = new ArrayList<>();

    private final List<Element> elements;

    public GraphsDetector(List<Element> elements) {
        this.elements = elements;
    }

    public int getNumberOfDetectedGraphs() {
        buildGraphs();
        return graphs.size();
    }

    private void buildGraphs() {
        List<Element> tmpElements = new ArrayList<>();
        for (Element element : elements) {
            List<Element> filteredElements = searchForElements(element);
            tmpElements.addAll(filteredElements);
        }
        elements.removeAll(tmpElements);
        if (!tmpElements.isEmpty()) {
            graphs.add(tmpElements);
        }
        elements.forEach(element ->
                graphs.add(List.of(element)));
    }

    private List<Element> searchForElements(Element element) {
        List<Integer> elementValuesAsList = getValuesAsList(element);
        return StreamEx.of(elements)
                .removeBy(Function.identity(), element)
                .filter(value ->
                        CollectionUtils.containsAny(getValuesAsList(value), elementValuesAsList))
                .toList();
    }

    private List<Integer> getValuesAsList(Element element) {
        return List.of(element.getFirst(), element.getSecond());
    }
}
