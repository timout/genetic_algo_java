package timout.algo.genetic.allones;

import java.util.stream.Stream;

public interface Population {

    Stream<Chromosome> stream();

    int size();

    double fitness();

    Chromosome last();

    Chromosome first();
}
