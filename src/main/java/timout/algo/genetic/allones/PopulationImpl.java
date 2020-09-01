package timout.algo.genetic.allones;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class PopulationImpl implements Population {

    private final List<Chromosome> population;
    private final Double fitness;

    private PopulationImpl(List<Chromosome> population, Double fitness) {
        this.population = population;
        this.fitness = fitness;
    }

    public Stream<Chromosome> stream() {
        return population.stream();
    }

    public int size() {
        return population.size();
    }

    @Override
    public double fitness() {
        return fitness;
    }

    @Override
    public Chromosome last() {
        return population.get(population.size()-1);
    }

    @Override
    public Chromosome first() {
        return population.get(0);
    }

    public static Builder builder(Function<List<Chromosome>, Double> fitnessFn) {
        return new Builder(fitnessFn);
    }

    public static class Builder {

        private final ArrayList<Chromosome> list = new ArrayList<>();
        private final Function<List<Chromosome>, Double> fitnessFn;

        public Builder(Function<List<Chromosome>, Double> fitnessFn) {
            this.fitnessFn = fitnessFn;
        }

        public Builder add(Chromosome c) {
            list.add(c);
            return this;
        }

        public Builder add(Builder b) {
            list.addAll(b.list);
            return this;
        }


        public PopulationImpl build() {
            list.sort(Comparator.comparingDouble(Chromosome::fitness).reversed());
            var l = List.copyOf(list);
            return new PopulationImpl(l, fitnessFn.apply(l));
        }

    }
}
