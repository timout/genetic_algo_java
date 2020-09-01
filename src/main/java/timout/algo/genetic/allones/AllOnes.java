package timout.algo.genetic.allones;

public class AllOnes {

    public static void main(String[] args) {
        int populationSize = 100;
        int chromosomeLength = 50;
        double mutationRate = 0.02;
        var crossoverRate = 0.95;
        int elitism = 2;
        var builderFactory = new PopulationBuilderFactory(new ChromosomeFitness(), new PopulationFitness());
        var populationFactory = new RandomPopulationFactory(populationSize, chromosomeLength, builderFactory);
        var mutator = new PopulationMutator(mutationRate, elitism, builderFactory);
        var parentSelector = new RouletteWheelParentSelector();
        var crossover = new PopulationCrossover(crossoverRate, elitism, parentSelector, builderFactory);
        var terminateCondition = new TerminateCondition();
        var ga = GeneticAlgo.newBuilder()
                .setPopulationFactory(populationFactory)
                .setMutator(mutator)
                .setCrossover(crossover)
                .setTerminateCondition(terminateCondition)
                .build();
        var population = ga.create();

        int generation = 1;

        //Fitness is evaluated on Population build: create, crossover, mutate
        while(! ga.canTerminate(population)) {
            System.out.printf("Best solution:%s%n", population.first().toString());
            population = ga.crossover(population);
            population = ga.mutate(population);
            generation += 1;
        }
        System.out.printf("Found solution in %d generations%n", generation);
        System.out.printf("Best solution: %s%n", population.first().toString());

    }
}
