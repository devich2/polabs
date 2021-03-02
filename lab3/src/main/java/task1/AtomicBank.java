/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task1;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author Devid
 */
public class AtomicBank implements IBank {
        public static final int NTEST = 10000;
    // added atomic int arr
    private final AtomicIntegerArray accounts;
    // added atomic ntransacts
    private final AtomicLong ntransacts = new AtomicLong(0);


    public AtomicBank(int n, int initialBalance) {
        accounts = new AtomicIntegerArray(n);
        for (int i = 0; i < n; i++) {
            accounts.set(i, initialBalance);
        }
    }

    public void transfer(int from, int to, int amount) {
        accounts.getAndAdd(from, -amount);
       
        accounts.getAndAdd(to, amount);
       

            if (ntransacts.incrementAndGet() % NTEST == 0) {
            test();
        }
      
    }

    public void test() {
        int sum = 0;
        for (int i = 0; i < accounts.length(); i++) {
            sum += accounts.get(i);
        }
        System.out.println("Transactions:" + ntransacts.get()
                + " Sum: " + sum);
    }

    public int size() {
        return accounts.length();
    }
}
