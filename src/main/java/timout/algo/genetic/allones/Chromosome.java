package timout.algo.genetic.allones;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;

public interface Chromosome {

    int length();

    double fitness();

    IntStream stream();

    int get(int position);

}
