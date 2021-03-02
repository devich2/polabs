/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Devid
 */
import common.MatrixResult;
import common.Result;
import fox.FoxAlgorithm;
import fox.MultiplicationAlgorithm;
import fox.MultiplicationUtils;
import task4.TextUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import task1.CharacteristicUtil;
import task1.ForkJoinWordLength;
import task1.WorkLength;
import task2.FoxForkJoinAlgorithm;
import task3.CommonWordsTask;
import task4.FileLooper;

public class Test {
    public static void main(String[] args) throws IOException {
        //testWordsLength();
        //testWordsLengthCompare();
        testMatrixMultiplication();
        //testCommonWords();
        //testFilesSearch();
    }

    private static void testWordsLength() throws IOException {
        String fileName = "C:\\Users\\Devid\\Desktop\\paral\\hungergames1.txt";

        List<String> textLines = TextUtils.readTextFromFileByLines(fileName);
        
        //long startTime = System.nanoTime();
        Map<String, Integer> wordsLength = ForkJoinWordLength.wordsCount(textLines);
        //long endTime = System.nanoTime();

        //long duration = (endTime - startTime) / 1000000;
        //System.out.println(duration);

        System.out.println("Expected value: " + CharacteristicUtil.getExpectedValue(wordsLength));
        System.out.println("Dispersion value: " + CharacteristicUtil.getDispersion(wordsLength));
    }
    
     private static void testWordsLengthCompare() throws IOException {
        String fileName = "C:\\Users\\Devid\\Desktop\\paral\\comparehunger.txt";
        
        List<String> textLines = TextUtils.readTextFromFileByLines(fileName);
        Map<Integer, Result> map = new TreeMap<>();
        int listCount = (int)textLines.stream().count();
        int divide = listCount / 20;
        for(int i = divide; i<=listCount; i+=divide) {
            map.put(i, testWordsLengthProgon(textLines.subList(0, i)));
        }
       
        System.out.println("Count lines:");
        for (Integer linesCount : map.keySet()) {
            System.out.printf("%4d ", linesCount);
        }
        System.out.println("\nUsual durations (in ms):");

        for (Result result : map.values()) {
            System.out.printf("%4d ", result.getUsualDuration());
        }

        System.out.println("\nForkJoin durations (in ms):");

        for (Result result : map.values()) {
            System.out.printf("%4d ", result.getForkJoinDuration());
        }
    }
    
     private static Result testWordsLengthProgon(List<String> lines){
         Result result = new Result();
         long startTime = System.currentTimeMillis();
         ForkJoinWordLength.wordsCount(lines);
         long endTime = System.currentTimeMillis();
         long difference = endTime - startTime;
         result.setForkJoinDuration(difference);
         
         startTime = System.currentTimeMillis();
         WorkLength.getWordsLength(lines);
         endTime = System.currentTimeMillis();
         difference = endTime - startTime;
         result.setUsualDuration(difference);
         return result;
     }
     
     private static void testMatrixMultiplication(){
        MultiplicationAlgorithm usualFox = new FoxAlgorithm();
        MultiplicationAlgorithm forkJoinFox = new FoxForkJoinAlgorithm();

        Map<Integer, MatrixResult> map = new TreeMap<>();
        for (int matrixSize = 200; matrixSize <= 2000; matrixSize += 200) {
        
            MatrixResult result = new MatrixResult(matrixSize);
            useAlgo(usualFox, matrixSize, result);
            System.gc();
            useAlgo(forkJoinFox, matrixSize, result);
            System.gc();
            map.put(matrixSize, result);
        }

        System.out.println("Matrix sizes:");
        for (Integer matrixSize : map.keySet()) {
            System.out.printf("%4d ", matrixSize);
        }
        System.out.println("\nUsual durations (in ms):");

        for (MatrixResult result : map.values()) {
            System.out.printf("%4d ", result.getUsualDuration());
        }

        System.out.println("\nFork join durations (in ms):");

        for (MatrixResult result : map.values()) {
            System.out.printf("%4d ", result.getForkJoinDuration());
        }

        System.out.println("\nfinished");

     }
      private static void useAlgo(MultiplicationAlgorithm algo, int matrixSize, MatrixResult result) {
//        System.out.printf("Executing %s algorithm\n", algo.getName());
//        System.out.printf("Creating matrices with size: %d\n", matrixSize);
        int[][] aMatrix = MultiplicationUtils.generateRandomMatrix(matrixSize, matrixSize);
        int[][] bMatrix = MultiplicationUtils.generateRandomMatrix(matrixSize, matrixSize);

//        MatrixHelper.printMatrix(aMatrix);
//        System.out.println();
//        MatrixHelper.printMatrix(bMatrix);
//        System.out.println();
        long startTime = System.currentTimeMillis();
//        MatrixHelper.printMatrix(result.getMatrix());
        algo.multiplyMatrix(aMatrix, bMatrix, result.getMatrix());
        long endTime = System.currentTimeMillis();
        long difference = endTime - startTime;
        if (algo.getName().equals("Usual")) {
            result.setUsualDuration(difference);
        } else {
            result.setForkJoinDuration(difference);
        }
//        System.out.printf("Time taken: %dms\n", difference);

//        MatrixHelper.printMatrix(result.getMatrix());
    }
    private static void testCommonWords() throws IOException {
        String firstText = "C:\\Users\\Devid\\Desktop\\paral\\hungergames1.txt";
        String secondText = "C:\\Users\\Devid\\Desktop\\paral\\hungergames3.txt";

        List<String> commonWords = CommonWordsTask.commonWords(TextUtils.readTextFromFileByLines(firstText),
                TextUtils.readTextFromFileByLines(secondText));

        System.out.println(commonWords.size());
    }

    private static void testFilesSearch() {
        String fileName = "D:\\forkjoinfolder";

        List<String> filesNames = new ArrayList<>();
        FileLooper.showFiles(new File(fileName).listFiles(), filesNames);

        for(String f : filesNames) {
            System.out.println(f);
        }

        System.out.println(filesNames.size());
    }
}
