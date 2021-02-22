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
public class Lector extends Assistant {
    public Lector{}
    public Lector(Journal j, String name) {
        super(j, name);
        setPriority(Thread.NORM_PRIORITY + 1);
    }
}
