package timout.algo.genetic.allones;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.IntStream;

public class ChromosomeImpl implements Chromosome{
    private final int [] genes;
    private final double fitness;

    private ChromosomeImpl(int [] genes, double fitness) {
        this.genes = genes;
        this.fitness = fitness;
    }

    public int length() {
        return genes.length;
    }

    public double fitness() {
        return this.fitness;
    }

    public int get(int position) {
        return this.genes[position];
    }

    public IntStream stream() {
        return Arrays.stream(genes);
    }

    @Override
    public String toString() {
        return Arrays.toString(genes);
    }

    public static Builder builder(Function<int [], Double> fitnessFn) {
        return new Builder(fitnessFn);
    }

    public static class Builder {
        private final ArrayList<Integer> list = new ArrayList<>();
        private final Function<int [], Double> fitnessFn;

        public Builder(Function<int [], Double> fitnessFn) {
            this.fitnessFn = fitnessFn;
        }

        public Builder add(int i) {
            list.add(i);
            return this;
        }

        public Builder add(Builder b) {
            list.addAll(b.list);
            return this;
        }

        public Builder merge(Builder b) {
            list.addAll(b.list);
            return this;
        }

        public Chromosome build() {
            var a = list.stream().mapToInt(i -> i).toArray();
            return new ChromosomeImpl(a, fitnessFn.apply(a));
        }
    }
}
