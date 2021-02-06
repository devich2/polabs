/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Devid
 */
public class BounceFrame extends JFrame{

    private BallCanvas canvas;
    public static final int WIDTH = 450;
    public static final int HEIGHT = 350;

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce programm");
        this.canvas = new BallCanvas();
        System.out.println("In Frame Thread name = " + Thread.currentThread().getName());
        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);
        JButton buttonStart = new JButton("Start");
        JButton buttonStop = new JButton("Stop");
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {   
                for (int i = 0; i < 1; i++) {
                    Ball b = new Ball(canvas, Thread.NORM_PRIORITY);
                    canvas.add(b);
                    BallThread thread = new BallThread(b);
                    thread.start();
                    System.out.println("Thread    name    =    " + thread.getName());
                }

            }
        });
        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        JButton buttonCreateRed = new JButton("Red");
        JButton buttonCreateBlue = new JButton("Blue");
        JButton buttonExperiment = new JButton("Experiment");
        
         buttonCreateRed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ball b = new Ball(canvas, Color.RED, Thread.MAX_PRIORITY);
                canvas.add(b);
                BallThread thread = new BallThread(b);
                thread.start();
                System.out.println("Thread name = " + thread.getName());
            }
        });

        buttonCreateBlue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ball b = new Ball(canvas, Color.BLUE, Thread.MIN_PRIORITY);
                canvas.add(b);
                BallThread thread = new BallThread(b);
                thread.start();
                System.out.println("Thread name = " + thread.getName());
            }
        });
        
        buttonExperiment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 1000; i++) {
                    Ball b = new Ball(canvas, Color.BLUE, Thread.NORM_PRIORITY);
                    canvas.add(b);
                }
                
                 //4 завдання
                BallThread previousBallThread = new BallThread();
                long wait = 500;
                for (int j = 0; j < canvas.getBalls().size(); j++) {
                    BallThread thread;
                    if (j == 0) {
                        thread = new BallThread(canvas.getBalls().get(j));
                    } else {
                        thread = new BallThread(canvas.getBalls().get(j), previousBallThread, 0);
                        wait = wait + 500;
                    }

                    previousBallThread = thread;
                    thread.start();
                }
            }
        });
         
        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonCreateRed);
        buttonPanel.add(buttonCreateBlue);
        buttonPanel.add(buttonExperiment);
        
        buttonPanel.add(buttonStop);
        content.add(buttonPanel, BorderLayout.SOUTH);
    }
}
