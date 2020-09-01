package timout.algo.genetic.allones;

import java.util.function.Function;
import java.util.function.Predicate;

public class RouletteWheelParentSelector implements Function<Population, Chromosome> {

    public Chromosome apply(Population p) {
        var fitness = p.fitness();
        var wheelPosition = fitness * Math.random();
        Predicate<Chromosome> sumFilter = new Predicate<>() {
            private double sum = 0.0;
            @Override
            public boolean test(Chromosome chromosome) {
                sum = sum + chromosome.fitness();
                return sum < wheelPosition;
            }
        };
        return p.stream().dropWhile(sumFilter).findFirst().orElse(p.last());
    }


}
