package timout.algo.genetic.allones;

import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;

public class PopulationCrossover implements UnaryOperator<Population> {

    private final int elitismCount;
    private final double crossoverRate;
    private final Function<Population, Chromosome> parentSelector;
    private final PopulationBuilderFactory factory;

    public PopulationCrossover(
            double crossoverRate,
            int elitismCount,
            Function<Population, Chromosome> parentSelector,
            PopulationBuilderFactory factory) {
        this.elitismCount = elitismCount;
        this.crossoverRate = crossoverRate;
        this.parentSelector = parentSelector;
        this.factory = factory;
    }

    @Override
    public Population apply(Population p) {
        var b = factory.populationBuilder();
        p.stream().limit(elitismCount).forEach(b::add);
        p.stream().skip(elitismCount).forEach( parent1 -> {
            var len = parent1.length();
            if ( this.crossoverRate > Math.random() ) {
                var parent2 = parentSelector.apply(p);
                var chromosome = IntStream.range(0, len).map(pos ->
                        ( Math.random() < 0.5) ? parent1.get(pos) : parent2.get(pos)
                ).collect(
                        factory::chromosomeBuilder,
                        ChromosomeImpl.Builder::add,
                        ChromosomeImpl.Builder::add).build();
                b.add(chromosome);
            } else {
                b.add(parent1);
            }
        });
        return b.build();
    }
}
