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
public class TapeMultiplyThread extends Thread{
     private int i;
    public int j;
    private int[] row;
    public int[] column;
    private int[][] resultMatrix;

    TapeMultiplyThread (int i, int[] row, int[][] resultMatrix) {
        this.i = i;
        this.row = row;
        this.resultMatrix = resultMatrix;
    }
    
    @Override
    public void run() {
        int result = 0;

        for (int i = 0; i < row.length; i++) {
            result += row[i] * column[i];
        }

        resultMatrix[i][j] = result;
    }
}
