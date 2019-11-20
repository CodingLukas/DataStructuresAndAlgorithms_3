package edu.ktu.ds.lab2.zilinskas;

import edu.ktu.ds.lab2.gui.ValidationException;
import edu.ktu.ds.lab2.utils.*;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class SimpleBenchmark1 {

    public static final String FINISH_COMMAND = "                       ";
    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle("edu.ktu.ds.lab2.gui.messages");

    private static final String[] BENCHMARK_NAMES = {"addBstRec", "addBstIte", "addAvlRec", "removeBst"};
    private static final int[] COUNTS = {10000, 20000, 40000, 80000};

    private final Timekeeper timeKeeper;
    private final String[] errors;

    private final SortedSet<Motorcycle> cSeries = new BstSet<>(Motorcycle.byPrice);
    private final SortedSet<Motorcycle> cSeries2 = new BstSetIterative<>(Motorcycle.byPrice);
    private final SortedSet<Motorcycle> cSeries3 = new AvlSet<>(Motorcycle.byPrice);

    /**
     * For console benchmark
     */
    public SimpleBenchmark1() {
        timeKeeper = new Timekeeper(COUNTS);
        errors = new String[]{
                MESSAGES.getString("badSetSize"),
                MESSAGES.getString("badInitialData"),
                MESSAGES.getString("badSetSizes"),
                MESSAGES.getString("badShuffleCoef")
        };
    }

    /**
     * For Gui benchmark
     *
     * @param resultsLogger
     * @param semaphore
     */
    public SimpleBenchmark1(BlockingQueue<String> resultsLogger, Semaphore semaphore) {
        semaphore.release();
        timeKeeper = new Timekeeper(COUNTS, resultsLogger, semaphore);
        errors = new String[]{
                MESSAGES.getString("badSetSize"),
                MESSAGES.getString("badInitialData"),
                MESSAGES.getString("badSetSizes"),
                MESSAGES.getString("badShuffleCoef")
        };
    }

    public static void main(String[] args) {
        executeTest();
    }

    public static void executeTest() {
        // suvienodiname skaičių formatus pagal LT lokalę (10-ainis kablelis)
        Locale.setDefault(new Locale("LT"));
        Ks.out("Greitaveikos tyrimas:\n");
        new SimpleBenchmark1().startBenchmark();
    }

    public void startBenchmark() {
        try {
            benchmark();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    private void benchmark() throws InterruptedException {
        try {
            for (int k : COUNTS) {
                Motorcycle[] motos = new MotorcyclesGenerator().generateShuffle(k, 1.0);
                cSeries.clear();
                cSeries2.clear();
                cSeries3.clear();
                timeKeeper.startAfterPause();

                timeKeeper.start();
                Arrays.stream(motos).forEach(cSeries::add);
                timeKeeper.finish(BENCHMARK_NAMES[0]);

                Arrays.stream(motos).forEach(cSeries2::add);
                timeKeeper.finish(BENCHMARK_NAMES[1]);

                Arrays.stream(motos).forEach(cSeries3::add);
                timeKeeper.finish(BENCHMARK_NAMES[2]);

                Arrays.stream(motos).forEach(cSeries::remove);

                timeKeeper.finish(BENCHMARK_NAMES[3]);
                timeKeeper.seriesFinish();
            }
            timeKeeper.logResult(FINISH_COMMAND);
        } catch (ValidationException e) {
            if (e.getCode() >= 0 && e.getCode() <= 3) {
                timeKeeper.logResult(errors[e.getCode()] + ": " + e.getMessage());
            } else if (e.getCode() == 4) {
                timeKeeper.logResult(MESSAGES.getString("allSetIsPrinted"));
            } else {
                timeKeeper.logResult(e.getMessage());
            }
        }
    }
}
