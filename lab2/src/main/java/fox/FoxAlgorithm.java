/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fox;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import main.MultiplicationAlgorithm;
/**
 *
 * @author Devid
 */
public class FoxAlgorithm implements MultiplicationAlgorithm {

    private static final int
            POOL_SIZE = Runtime.getRuntime().availableProcessors();

    private final ExecutorService exec = Executors.newFixedThreadPool(POOL_SIZE);

    @Override
    public void multiplyMatrix(int[][] firstMatrix, int[][] secondMatrix, int[][] result) {
        Future f = exec.submit(new FoxMultiplyThread(firstMatrix, secondMatrix, result,
                0, 0, 0, 0, 0, 0, firstMatrix.length, this.exec));
        
       
        try {
            f.get();
           
        } catch (Exception ignored) {

        }
        //exec.shutdown();
    }

    @Override
    public String getName() {
        return "Fox";
    }
}