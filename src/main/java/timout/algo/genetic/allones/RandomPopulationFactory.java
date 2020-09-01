package timout.algo.genetic.allones;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomPopulationFactory implements Supplier<Population> {

    private final int populationSize;
    private final int chromosomeLength;
    private final PopulationBuilderFactory factory;

    public RandomPopulationFactory(int populationSize,
                                   int chromosomeLength,
                                   PopulationBuilderFactory factory) {
        this.populationSize = populationSize;
        this.chromosomeLength = chromosomeLength;
        this.factory = factory;
    }

    @Override
    public Population get() {
        return IntStream.range(0, populationSize)
                .mapToObj(i -> create())
                .collect( factory::populationBuilder,
                        PopulationImpl.Builder::add,
                        PopulationImpl.Builder::add
                        ).build();
    }

    public Chromosome create() {
        return new Random()
                .ints(chromosomeLength, 0, 2)
                .collect(factory::chromosomeBuilder,
                        ChromosomeImpl.Builder::add,
                        ChromosomeImpl.Builder::add)
                .build();
    }

}
