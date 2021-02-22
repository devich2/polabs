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
public class Test {
        public static void main(String[] args) {
        Journal j = new Journal("PRO",1, 3, 30);
        Lector l = new Lector(j, "Lector");
        Assistant a1 = new Assistant(j, "Assistant 1");
        Assistant a2 = new Assistant(j, "Assistant 2");
        Assistant a3 = new Assistant(j, "Assistant 3");
        Assistant[] assistants = new Assistant[]{l, a1, a2, a3,new Assistant(j, "Assistant 3"),new Assistant(j, "Assistant 3"),new Assistant(j, "Assistant 3"),new Assistant(j, "Assistant 3"),new Assistant(j, "Assistant 3"),new Assistant(j, "Assistant 3"),new Assistant(j, "Assistant 3")};
        for (Assistant a : assistants) {
            a.start();
        }


        try {
            for (Assistant a : assistants) {
                a.join();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Journal is done");
        j.print();

    }

}
