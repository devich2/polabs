/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task4;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;

/**
 *
 * @author Devid
 */
public class Ball {
    private BallCanvas canvas;
    private static final int XSIZE = 20;
    private static final int YSIZE = 20;
    private int x = 0;
    private int y = 0;
    private int dx = 2;
    private int dy = 2;
    private final int priority;
    private Color color;
    public Ball(BallCanvas c, Color color, int priority) {
        this.canvas = c;
        this.priority = priority;
        this.color = color;
        this.x = 0;
        this.y = 0;
    }
    
    public Ball(BallCanvas c, int threadPriority) {
        this.canvas = c;
        this.priority = threadPriority;
        if (Math.random() < 0.5) {
            x = new Random().nextInt(this.canvas.getWidth());
            y = 0;
        } else {
            x = 0;
            y = new Random().nextInt(this.canvas.getHeight());
        }
    }
    
    public int getThreadPriority(){
        return this.priority;
    }
  

    public void draw(Graphics2D g2) {
        g2.setColor(this.color == null ? Color.darkGray : this.color);
        g2.fill(new Ellipse2D.Double(x, y, XSIZE, YSIZE));
    }

    public void move() {
        x += dx;
        y += dy;
        if (x < 0) {
            x = 0;
            dx = -dx;
        }
        if (x + XSIZE >= this.canvas.getWidth()) {
            x = this.canvas.getWidth() - XSIZE;
            dx = -dx;
        }
        if (y < 0) {
            y = 0;
            dy = -dy;
        }
        if (y + YSIZE >= this.canvas.getHeight()) {
            y = this.canvas.getHeight() - YSIZE;
            dy = -dy;
        }
        if(this.check(this.x, this.y)){
                        System.out.println("In loose");
            System.out.println("x = " + x);
            System.out.println("y = " + y);

            Thread.currentThread().interrupt();
            this.canvas.incrementBallsCount();
            this.canvas.deleteBall(this);
        }
        this.canvas.repaint();
    }
    
    public boolean check(int x, int y){
        return (this.x + XSIZE >= this.canvas.getWidth() && y + YSIZE >= this.canvas.getHeight())
                || (this.x == 0 && this.y == 0)
                || (this.x == 0 && this.y + this.YSIZE >= this.canvas.getHeight())
                || (this.y == 0 && this.x + this.XSIZE >= this.canvas.getWidth());
    }
}
