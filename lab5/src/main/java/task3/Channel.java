/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task3;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author Чмерук Оля
 */
public class Channel implements Runnable {

    private String name;
    private double delayAvg;
    private double delayDev;
    private String distribution;
    private Model model;
    private double delay = 0;
    private Results result;


    public Channel(String name, Model model, double delayAvg, double delayDev, String distribution,
                   Results result){
        this.name = name;
        this.model = model;
        this.delayAvg = delayAvg;
        this.delayDev = delayDev;
        this.distribution = distribution;
        this.result = result;
    }

    @Override
    public void run() {
        while (model.getTCurrent()< model.timeModeling){
            try {
                model.processQueue.poll(1, TimeUnit.NANOSECONDS);
                result.avgQueueCount();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            result.incrementDisposed();
            delay = Distributions.delay(distribution, delayAvg, delayDev);
            model.incrementTCurrent(delay);
            try {
                Thread.sleep((long) delay);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }
}
