/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task4;

/**
 *
 * @author Devid
 */
public interface MultiplicationAlgorithm {
    String getName();
    void multiplyMatrix(int[][] aMatrix, int[][] bMatrix, int[][] cMatrix, int poolSize);
}