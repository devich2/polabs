/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task4;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author Devid
 */
public class FoxAlgorithm implements MultiplicationAlgorithm {
    
    @Override
    public String getName() {
        return "Fox";
    }

    @Override
    public void multiplyMatrix(int[][] aMatrix, int[][] bMatrix, int[][] cResult, int poolSize) {
        ExecutorService exec = Executors.newFixedThreadPool(poolSize);

        Future f = exec.submit(new FoxMultiplyThread(aMatrix, bMatrix, cResult,
                0, 0, 0, 0, 0, 0, aMatrix.length, exec));

        try {
            f.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
//        exec.shutdown();
    }

}
