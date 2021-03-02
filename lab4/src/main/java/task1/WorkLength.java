/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Devid
 */
public class WorkLength {
    
    public static Map<String, Integer> getWordsLength(List<String> lines) {
        Map<String, Integer> wordsLength = new HashMap<>();

        for (String line : lines) {
            String[] words = line.split("\\W+");

            for (String word : words) {
                if (word.length() > 0) {
                    wordsLength.putIfAbsent(word, word.length());
                }
            }
        }

        return wordsLength;
    }
}
