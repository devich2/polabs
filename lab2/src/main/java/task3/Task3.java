/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task3;

import fox.FoxAlgorithm;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import main.MultiplicationAlgorithm;
import main.MultiplicationUtils;
import tape.TapeAlgorithm;

/**
 *
 * @author Devid
 */
public class Task3 {

    private static final int MATRIX_SIZE = 1000;
    private static final int MATRIX_SIZE_STEP = 20;

    public static void main(String[] args) {
        MultiplicationAlgorithm tapeAlgorithm = new TapeAlgorithm();
        MultiplicationAlgorithm foxAlgorithm = new FoxAlgorithm();

        Map<Integer, Result> map = new TreeMap<>();
        for (int matrixSize = 2; matrixSize <= MATRIX_SIZE; matrixSize += MATRIX_SIZE_STEP) {
            //System.out.println(matrixSize);
            Result result = new Result(matrixSize);
            useAlgo(tapeAlgorithm, matrixSize, result);
            useAlgo(foxAlgorithm, matrixSize, result);

            map.put(matrixSize, result);
        }

        System.out.println("Matrix sizes:");
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

        System.out.println("\nfinished");

    }

    private static void useAlgo(MultiplicationAlgorithm algo, int matrixSize, Result result) {
//        System.out.printf("Executing %s algorithm\n", algo.getName());
//        System.out.printf("Creating matrices with size: %d\n", matrixSize);
        int[][] aMatrix = MultiplicationUtils.generateRandomMatrix(matrixSize, matrixSize);
        int[][] bMatrix = MultiplicationUtils.generateRandomMatrix(matrixSize, matrixSize);

//        MatrixHelper.printMatrix(aMatrix);
//        System.out.println();
//        MatrixHelper.printMatrix(bMatrix);
//        System.out.println();
        long startTime = System.currentTimeMillis();
//        MatrixHelper.printMatrix(result.getMatrix());
        algo.multiplyMatrix(aMatrix, bMatrix, result.getMatrix());
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
