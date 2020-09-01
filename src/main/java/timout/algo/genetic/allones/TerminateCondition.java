package timout.algo.genetic.allones;

import java.util.function.Predicate;

public class TerminateCondition implements Predicate<Population> {

    @Override
    public boolean test(Population p) {
        return p.stream().anyMatch(i -> i.fitness() == 1);
    }
}
