/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task4;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Devid
 */
public class BallCanvas extends JPanel{
    private ArrayList<Ball> balls = new ArrayList<>();
    private AtomicInteger ballsCount = new AtomicInteger(0);
    
    public void add(Ball b) {
        this.balls.add(b);
    }
    
    public ArrayList<Ball> getBalls(){
        return balls;
    } 
    
    public void deleteBall(Ball removed){
        balls.remove(removed);
    }
    
    public void incrementBallsCount(){
        this.ballsCount.incrementAndGet();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < balls.size(); i++) {
            Ball b = balls.get(i);
            b.draw(g2);
        }
        g.drawString("Balls in pots: " + ballsCount.get(), 50, 10);
    }
}
