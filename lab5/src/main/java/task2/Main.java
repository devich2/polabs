package task2;

import javax.management.AttributeValueExp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        double avgQueueLength = 0;
        double avgFailureProbability = 0;

        Create create = new Create(0.1, 0.0, "Uniform");
        Process process = new Process(0.6, 0.0, "Uniform", 2, 1);

        ExecutorService modelPool = Executors.newSingleThreadExecutor();
        ArrayList<Model> tasks = new ArrayList<>();
        List<Future<Result>> resultList;
        for (int i=0; i<7; i++){
            tasks.add(new Model(process, create, 10000.0));
        }
        resultList = modelPool.invokeAll(tasks);
        modelPool.shutdown();
        System.out.println("\n--------------------Result List--------------------");

        for (int i = 0; i < resultList.size(); i++) {
            Future<Result> future = resultList.get(i);
            try {
                Result result = future.get();
                avgFailureProbability += result.getFailureProbability();
                avgQueueLength += result.getAvgQueue();
                System.out.println("Run: " + (i+1) + ": Failure probability = " + result.getFailureProbability()
                        + "% ; Average queue length = " + result.getAvgQueue());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        avgFailureProbability /= resultList.size();
        avgQueueLength /= resultList.size();

        System.out.println("\n--------------------Average result--------------------");
        System.out.println("Average failure probability: " + avgFailureProbability);
        System.out.println("Average queue length: " + avgQueueLength);

    }
}
