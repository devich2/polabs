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

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Create create = new Create(2.0, 0.0, "Uniform");
        Process process = new Process(3.0, 0.0, "Uniform", 2, 10);
        Model model = new Model(process, create, 10000.0);
        model.simulation();
    }

}
