/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Devid
 */
public interface MultiplicationAlgorithm {
       String getName();
       void multiplyMatrix(int[][] firstMatrix, int[][] secondMatrix, int[][] resultMatrix);
}
