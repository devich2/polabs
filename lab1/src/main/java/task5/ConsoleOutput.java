/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task5;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Devid
 */
class Printer {

    private int row;
    private int rowCount;
    private boolean isHorizontal;

    synchronized void printHorizontal() {
        if (isHorizontal) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

        if (row != 100) {
            System.out.print("-");
            row++;
        } else {
            System.out.println("-");
            if (++rowCount == 100) {
                Thread.currentThread().interrupt(); 
                return;
            }
            row = 0;
        }
        isHorizontal = true;
        notify();
    }

    synchronized void printVertical() {
        if (!isHorizontal) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

        if (row != 100) {
            System.out.print("|");
            row++;
        } else {
            System.out.println("|");
            if (++rowCount == 100) {
                Thread.currentThread().interrupt();
                return;
            }
            row = 0;
        }

        isHorizontal = false;
        notify();
    }
}

public class HorizontalThread extends Thread {

    private Printer printer;

    HorizontalThread(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            printer.printHorizontal();
        }
    }
}

public class VerticalThread extends Thread {

    private Printer printer;

    VerticalThread(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            printer.printVertical();
        }
    }
}

public class ConsoleOutput {

    public static void main(String[] args) {

//        Thread first = new Thread(() -> {
//            while (true) {
//                System.out.print("?");
//                Thread.yield();
//            }
//        });
//        Thread second = new Thread(() -> {
//            while (true) {
//                System.out.print(",");
//                Thread.yield();
//            }
//        });
//        
       
//        first.start();
//        second.start();
        Printer printer = new Printer();
        HorizontalThread horizontalThread = new HorizontalThread(printer);
        VerticalThread verticalThread = new VerticalThread(printer);

        horizontalThread.start();
        verticalThread.start();

    }
}
