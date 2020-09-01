package timout.algo.genetic.allones;

import java.util.function.UnaryOperator;

public class PopulationMutator implements UnaryOperator<Population> {

    private final double mutationRate;
    private final int elitismCount;

    private final PopulationBuilderFactory factory;

    public PopulationMutator(
            double mutationRate, int elitismCount, PopulationBuilderFactory factory) {
        this.mutationRate = mutationRate;
        this.elitismCount = elitismCount;
        this.factory = factory;
    }

    @Override
    public Population apply(Population p) {
        var b = factory.populationBuilder();
        p.stream().limit(elitismCount).forEach(b::add);
        p.stream().skip(elitismCount).forEach( c -> b.add(mutate(c)));
        return b.build();
    }

    private Chromosome mutate(Chromosome c) {
        return c.stream().map( g -> {
            var r = Math.random();
            return ( mutationRate > r ) ? g ^ 1 : g;
        }).collect(factory::chromosomeBuilder,
                ChromosomeImpl.Builder::add,
                ChromosomeImpl.Builder::merge)
                .build();
    }

}
