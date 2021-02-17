/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task4;

import java.util.Map;
import java.util.TreeMap;
import main.MultiplicationUtils;
import task3.Result;

/**
 *
 * @author Devid
 */
public class Task4 {
    private static final int MATRIX_SIZE = 500;
    private static final int
            POOL_SIZE = Runtime.getRuntime().availableProcessors();


    public static void main(String[] args) {
        task4.MultiplicationAlgorithm tapeAlgorithm = new TapeAlgorithm();
        task4.MultiplicationAlgorithm foxAlgorithm = new FoxAlgorithm();

        Map<Integer, Result> map = new TreeMap<>();
        for (int poolSize = 1; poolSize <= 20; poolSize++) {
            Result result = new Result(MATRIX_SIZE);
            useAlgo(tapeAlgorithm, poolSize, result);
            useAlgo(foxAlgorithm, poolSize, result);

            map.put(poolSize, result);
        }

        System.out.println("Proc numbers:");
        for (Integer matrixSize : map.keySet()) {
            System.out.printf("%4d ", matrixSize);
        }
        System.out.println("\nTape durations (in ms):");

        for (Result result : map.values()) {
            System.out.printf("%4d ", result.getTapeDuration());
        }

        System.out.println("\nFox durations (in ms):");

        for (Result result : map.values()) {
            System.out.printf("%4d ", result.getFoxDuration());
        }
        System.out.println("Finished");

    }

    private static void useAlgo(MultiplicationAlgorithm algo, int poolSize, Result result) {
//        System.out.printf("Executing %s algorithm\n", algo.getName());
//        System.out.printf("Creating matrices with size: %d\n", MATRIX_SIZE);
        int[][] aMatrix = MultiplicationUtils.generateRandomMatrix(MATRIX_SIZE, MATRIX_SIZE);
        int[][] bMatrix = MultiplicationUtils.generateRandomMatrix(MATRIX_SIZE, MATRIX_SIZE);

//        MatrixHelper.printMatrix(aMatrix);
//        System.out.println();
//        MatrixHelper.printMatrix(bMatrix);
//        System.out.println();


        long startTime = System.currentTimeMillis();
//        MatrixHelper.printMatrix(result.getMatrix());
        algo.multiplyMatrix(aMatrix, bMatrix, result.getMatrix(), poolSize);
        long endTime = System.currentTimeMillis();
        long difference = endTime - startTime;
        if (algo.getName().equals("Fox")) {
            result.setFoxDuration(difference);
        } else {
            result.setTapeDuration(difference);
        }
//        System.out.printf("Time taken: %dms\n", difference);

//        MatrixHelper.printMatrix(result.getMatrix());

    }
}
