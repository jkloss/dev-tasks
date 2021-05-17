package pl.jkloss.tasks.three;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
class Element {

    private final int first;
    private final int second;

    public Element(int first, int second) {
        this.first = first;
        this.second = second;
    }
}
