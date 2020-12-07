package jmh;

import findElementIndexAlgorithms.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 1)
@Measurement(iterations = 2)
public class FindAlgorithmsBenchmarks {

    @Param({"BinarySearch", "SequentialSearch"})
    private String algorithmName;

    private FindAlgorithm algorithm;

    @Param({"1000", "10000", "100000"})
    private int DATA_SIZE;

    private int[] data;

    private int dataIndex;

    private int dataValue;

    private int seed = 111;

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(FindAlgorithmsBenchmarks.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Setup(Level.Trial)
    public void dataSetup(){
        data = new Random(seed).ints(DATA_SIZE).toArray();
        Arrays.sort(data);
    }

    @Setup(Level.Iteration)
    public void setup() {
        switch (algorithmName) {
            case "BinarySearch":
                algorithm = new BinarySearch(data);
                break;
            case "SequentialSearch":
                algorithm = new SequentialSearch(data);
                break;
        }
    }

    @Setup(Level.Invocation)
    public void dataIndexSetup(){
        dataIndex = new Random(seed).nextInt(DATA_SIZE);
        dataValue = data[dataIndex];
    }

    @Benchmark
    public void benchmark() {
        algorithm.findElementIndex(dataValue);
    }
}
