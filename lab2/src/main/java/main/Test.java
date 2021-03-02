/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import fox.FoxAlgorithm;
import static main.MultiplicationUtils.generateRandomMatrix;
import tape.TapeAlgorithm;

/**
 *
 * @author Devid
 */
public class Test {

    private static final int MATRIX_SIZE = 500;

    public static void main(String[] args) {
        //MultiplicationAlgorithm tapeAlgorithm = new TapeAlgorithm();
        MultiplicationAlgorithm foxAlgorithm = new FoxAlgorithm();
        useAlgo(foxAlgorithm);
    }

    private static void useAlgo(MultiplicationAlgorithm algo) {
        System.out.printf("Executing %s algorithm\n", algo.getName());
        System.out.printf("Creating matrices with size: %d\n", MATRIX_SIZE);
        Result result = new Result(MATRIX_SIZE);
        int[][] aMatrix = MultiplicationUtils.generateRandomMatrix(MATRIX_SIZE, MATRIX_SIZE);
        int[][] bMatrix = MultiplicationUtils.generateRandomMatrix(MATRIX_SIZE, MATRIX_SIZE);

//       MultiplicationUtils.printMatrix(aMatrix);
//       System.out.println();
//       MultiplicationUtils.printMatrix(bMatrix);
//        System.out.println();

        long startTime = System.currentTimeMillis();
        algo.multiplyMatrix(aMatrix, bMatrix, result.getMatrix());
        long endTime = System.currentTimeMillis();
        long difference = endTime - startTime;
        System.out.printf("Time taken: %dms\n", difference);

       //MultiplicationUtils.printMatrix(result.getMatrix());
    }
}
