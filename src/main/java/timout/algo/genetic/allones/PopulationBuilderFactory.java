package timout.algo.genetic.allones;

import java.util.List;
import java.util.function.Function;

public class PopulationBuilderFactory {

    private final Function<int [], Double> chromosomeFitnessFn;
    private final Function<List<Chromosome>, Double> populationFitnessFn;

    public PopulationBuilderFactory(
            Function<int[], Double> chromosomeFitnessFn,
            Function<List<Chromosome>, Double> populationFitnessFn) {
        this.chromosomeFitnessFn = chromosomeFitnessFn;
        this.populationFitnessFn = populationFitnessFn;
    }

    public PopulationImpl.Builder populationBuilder() {
        return PopulationImpl.builder(populationFitnessFn);
    }

    public ChromosomeImpl.Builder chromosomeBuilder() {
        return ChromosomeImpl.builder(chromosomeFitnessFn);
    }
}
