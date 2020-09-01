package timout.algo.genetic.allones;

import java.util.List;
import java.util.function.Function;

public class PopulationFitness implements Function<List<Chromosome>, Double> {

    @Override
    public Double apply(List<Chromosome> population) {
        return population.stream()
                .map(Chromosome::fitness)
                .reduce(Double::sum).orElse(0.0) / population.size();
    }
}
