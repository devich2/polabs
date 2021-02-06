/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task3;



/**
 *
 * @author Devid
 */
public class BallThread extends Thread {
    private Ball b;
    public BallThread(Ball ball) {
        b = ball;
        this.setPriority(ball.getThreadPriority());
    }
    
    @Override
    public void run() {
        try {
            for (int i = 1; i < 10000; i++) {
                b.move();
                System.out.println("Thread name = " + Thread.currentThread().getName());
                Thread.sleep(200);
            }
        } catch (InterruptedException ex) {
        }
    }
}
