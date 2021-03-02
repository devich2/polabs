/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author Devid
 */
public class MatrixResult extends Result {
    private int[][] matrix;
    
    public MatrixResult(int matrixSize) {
        super(matrixSize);
        this.matrix = new int[matrixSize][matrixSize];
    }
    public int[][] getMatrix(){
        return this.matrix;
    }
}
