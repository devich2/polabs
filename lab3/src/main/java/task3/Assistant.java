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
public class Assistant extends Thread {

    private final Journal journal;
    private final String name;
    public Assistant(){ }
    
    public Assistant(Journal j, String name) {
        this.journal = j;
        this.name = name;
    }

    public void run() {
        while (!journal.isFull()) {
            journal.setNextMark();
        }
        System.out.printf("%s ended\n", name);
    }
}

