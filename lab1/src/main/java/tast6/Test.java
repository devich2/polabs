/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tast6;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Devid
 */
public class Test {

    public static void main(String[] args) {
        Counter counter = new Counter();

        for (int i = 0; i < 10; i++) {
            Thread incrementer = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100000; i++) {
  
                            counter.increment();
                  
                        
                    }
                }

            }
            );

            Thread decrementer = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100000; i++) {
                      
                            counter.decrement();
                   
                    }
                }

            }
            );

            incrementer.start();
            decrementer.start();

//            try {
//                incrementer.join();
//                decrementer.join();
//            } catch (InterruptedException e) {
//                System.out.println("Interrupted");
//            }
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(counter.getCounter());
    }
}
