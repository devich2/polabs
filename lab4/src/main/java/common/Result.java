/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author Devid
 */
public class Result {
    private long usualDuration;
    private long forkJoinDuration;
    private int unitsSize;
    public Result(){
        
    }
    public Result(int unitsSize){
       this.unitsSize = unitsSize;
    }
    public long getUsualDuration() {
        return usualDuration;
    }

    public void setUsualDuration(long usualDuration) {
        this.usualDuration = usualDuration;
    }

    public long getForkJoinDuration() {
        return forkJoinDuration;
    }

    public void setForkJoinDuration(long forkJoinDuration) {
        this.forkJoinDuration = forkJoinDuration;
    }
}

