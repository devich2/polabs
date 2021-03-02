/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2;

import java.util.Random;

/**
 *
 * @author Devid
 */
public class Consumer implements Runnable {
    private Drop drop;
    private int maxSize;

    Consumer(Drop drop, int maxSize) {
        this.drop = drop;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int number = drop.take();
             number != ++maxSize;
             number = drop.take()) {
            System.out.format("MESSAGE RECEIVED: %s%n", number);
          
        }
    }
}
