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

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

public class Process{

    private double delayAvg;
    private double delayDev;
    private String distribution;
    private int channelsNum;
    private int queueCapacity;
    private Model model;
    private Result result;


    public Process(double delayAvg, double delayDev, String distribution, int channelsNum, int queueCapacity){
        this.delayAvg = delayAvg;
        this.delayDev = delayDev;
        this.distribution = distribution;
        this.channelsNum = channelsNum;
        this.queueCapacity = queueCapacity;
    }

    public ArrayList<Channel> channels(){
        ArrayList<Channel> channels = new ArrayList<>();
        for(int i=0; i<channelsNum; i++){
            channels.add(new Channel("Channel" + (i+1), model, delayAvg, delayDev, distribution, result));
        }
        return channels;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public int getQueueCapacity() {
        return queueCapacity;
    }

    public void setStatistics(Result result) {
        this.result = result;
    }
}
