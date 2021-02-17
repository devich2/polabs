/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.MultiplicationAlgorithm;

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
    public void multiplyMatrix(int[][] firstMatrix, int[][] secondMatrix, int[][] resultMatrix) {
       int rowsCount = firstMatrix.length;
        List<TapeMultiplyThread> threads = new ArrayList<>();
        
        for (int i = 0; i < rowsCount; i++) {
            int[] row = firstMatrix[i];

            TapeMultiplyThread thread = new TapeMultiplyThread(i, row, resultMatrix);
            threads.add(thread);
        }

        for (int i = 0; i < rowsCount; i++) {
            int[] column = getColumn(secondMatrix, i);

            for (TapeMultiplyThread thread : threads) {
                thread.column = column;
                thread.j = i;
                thread.run();
            }

            for (TapeMultiplyThread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private int[] getColumn(int[][] matrix, int column) {
        return Arrays.stream(matrix).mapToInt(ints -> ints[column]).toArray();
    }
}
