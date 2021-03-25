/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task1;

/**
 *
 * @author Devid
 */

import java.util.concurrent.BlockingQueue;

public class Create implements Runnable{

    private double delayAvg;
    private double delayDev;
    private String distribution;
    private Model model;
    private Result result;

    public Create(double delayAvg, double delayDev, String distribution){
        this.delayAvg = delayAvg;
        this.delayDev = delayDev;
        this.distribution = distribution;
    }

    @Override
    public void run() {
        while (model.getTCurrent()<model.timeModeling){
            if (model.processQueue.offer(new Task())){
                result.avgQueueCount();
            }else {
                result.incrementFailed();
            }
           // System.out.println("Action in Creator, time=" + model.getTCurrent());
            double delay = Distributions.delay(distribution, delayAvg, delayDev);
            model.incrementTCurrent(delay);
            try {
                Thread.sleep((long) delay);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setStatistics(Result result) {
        this.result = result;
    }
}
