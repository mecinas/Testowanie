package jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import sortAlgorithms.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 1)
@Measurement(iterations = 2)
public class SortAlgorithmsBenchmarks {

    @Param({"BubbleSort", "InsertSort", "MergeSort", "Qsort", "JavaDefault"})
    private String algorithmName;

    private SortAlgorithm algorithm;

    @Param({"1000", "10000", "100000"})
    private int DATA_SIZE;

    private int[] data;

    private int seed = 111;

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(SortAlgorithmsBenchmarks.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Setup(Level.Iteration)
    public void setup() {
        switch (algorithmName) {
            case "BubbleSort":
                algorithm = new BubbleSort();
                break;
            case "InsertSort":
                algorithm = new InsertSort();
                break;
            case "MergeSort":
                algorithm = new MergeSort();
                break;
            case "Qsort":
                algorithm = new Qsort();
                break;
            case "JavaDefault":
                algorithm = new JavaDefaultSort();
                break;
        }
    }

    @Setup(Level.Invocation)
    public void dataSetup(){
        data = new Random(seed).ints(DATA_SIZE).toArray();
    }

    @Benchmark
    public void benchmark() {
        algorithm.sort(data);
    }
}

