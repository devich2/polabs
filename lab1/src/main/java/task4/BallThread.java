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
public class BallThread extends Thread {
    private Ball b;
    private BallThread previousBallThread;
    private long wait = 0;
    public BallThread(Ball ball) {
        b = ball;
        this.setPriority(ball.getThreadPriority());
    }
    
    public BallThread() {
    }

    BallThread(Ball ball, BallThread ballThread, long wait) {
        this(ball);
        this.previousBallThread = ballThread;
        this.wait = wait;
    }
    
    @Override
    public void run() {
        try {
            if (this.previousBallThread != null) {
                if(wait != 0){
                    this.previousBallThread.join(wait);
                }
                else{
                    this.previousBallThread.join();
                }
            }

            for (int i = 1; i < 10000; i++) {
                b.move();
                System.out.println("Thread name = " + Thread.currentThread().getName());
                Thread.sleep(10);
            }
        } catch (InterruptedException ex) {
        }
    }
}
