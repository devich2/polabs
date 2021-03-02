/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2;

/**
 *
 * @author Devid
 */

import fox.MultiplicationAlgorithm;
import java.util.concurrent.ForkJoinPool;

public class FoxForkJoinAlgorithm implements MultiplicationAlgorithm {
    @Override
    public void multiplyMatrix(int[][] firstMatrix, int[][] secondMatrix, int[][] resultMatrix) {
        ForkJoinPool pool = new ForkJoinPool(10);

        pool.submit(new FoxForkJoinTask(firstMatrix, secondMatrix, resultMatrix,
                0, 0, 0, 0, 0, 0, firstMatrix.length)).join();
    }

    @Override
    public String getName() {
        return "ForkJoin";
    }
}
