package timout.algo.genetic.allones;

import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class GeneticAlgo {

    private final Supplier<Population> populationFactory;

    private final UnaryOperator<Population> mutator;
    private final UnaryOperator<Population> crossover;
    private final Predicate<Population> terminateCondition;

    private GeneticAlgo(Builder b) {
        this.populationFactory = b.populationFactory;
        this.mutator = b.mutator;
        this.crossover = b.crossover;
        this.terminateCondition = b.terminateCondition;
    }

    public Population create() {
        return populationFactory.get();
    }

    public Population crossover(Population p) {
        return this.crossover.apply(p);
    }

    public Population mutate(Population p) {
        return mutator.apply(p);
    }

    public boolean canTerminate(Population p) {
        return terminateCondition.test(p);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private Supplier<Population> populationFactory;
        private UnaryOperator<Population> mutator;
        private UnaryOperator<Population> crossover;
        private Predicate<Population> terminateCondition;

        public Builder setPopulationFactory(Supplier<Population> populationFactory) {
            this.populationFactory = populationFactory;
            return this;
        }

        public Builder setMutator(UnaryOperator<Population> mutator) {
            this.mutator = mutator;
            return this;
        }

        public Builder setCrossover(UnaryOperator<Population> crossover) {
            this.crossover = crossover;
            return this;
        }

        public Builder setTerminateCondition(Predicate<Population> terminateCondition) {
            this.terminateCondition = terminateCondition;
            return this;
        }

        public GeneticAlgo build() {
            return new GeneticAlgo(this);
        }
    }
}
