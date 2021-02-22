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
public class Student {
    private static int seqId = 0;
    private final int group;
    private final int id;

    public Student(int group) {
        seqId++;
        this.group = group;
        this.id = seqId;
    }

    public int getId() {
        return id;
    }

    public int getGroup() {
        return group;
    }

}
