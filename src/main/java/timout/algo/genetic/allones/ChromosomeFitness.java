package timout.algo.genetic.allones;

import java.util.Arrays;
import java.util.function.Function;

public class ChromosomeFitness implements Function<int [], Double> {

    @Override
    public Double apply(int [] genes) {
        return ((double) Arrays.stream(genes).sum()) / genes.length;
    }
}
