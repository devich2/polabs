/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author Devid
 */
public class TapeAlgorithm implements MultiplicationAlgorithm{
        @Override
    public String getName() {
        return "Tape";
    }

    
    @Override
    public void multiplyMatrix(int[][] firstMatrix, int[][] secondMatrix, int[][] resultMatrix, int poolSize) {
               ExecutorService exec = Executors.newFixedThreadPool(poolSize);

        int rowsCount = firstMatrix.length;

        ArrayList<Future> futures = new ArrayList<>();

        for (int i = 0; i < rowsCount; i++) {
            int[] row = firstMatrix[i];


            for (int j = 0; j < rowsCount; j++) {
                int[] column = getColumn(secondMatrix, j);

                TapeMultiplyThread thread = new TapeMultiplyThread(i, row, resultMatrix);
                thread.column = column;
                thread.j = j;

                Future f = exec.submit(thread);
                futures.add(f);

            }
        }


        for (Future f : futures) {
            try {
                f.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

    }

    private int[] getColumn(int[][] matrix, int column) {
        return Arrays.stream(matrix).mapToInt(ints -> ints[column]).toArray();
    }
}