/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2;

/**
 *
 * @author Devid
 */

public class ProducerConsumerExample {
    public static void main(String[] args) {
        int size = 5;

        Drop drop = new Drop();
        (new Thread(new Producer(drop, size))).start();
        (new Thread(new Consumer(drop, size))).start();
    }
}

