package com.etaTech.Starvation;

import com.etaTech.AnsiColors;

import java.util.concurrent.locks.ReentrantLock;


/****************************************************
 *** Created by Fady Fouad on 6/13/2019 at 14:25.***
 ***************************************************/
public class Main {
    private static final ReentrantLock lock = new ReentrantLock(true); //true ==> Fair lock

    public static void main(String[] args) {
        System.out.println("\n-------------------------Starvation----------------------\n");

        Thread thread1 = new Thread(new Worker(AnsiColors.ANSI_YELLOW), "Priority 1");
        Thread thread2 = new Thread(new Worker(AnsiColors.ANSI_CYAN), "Priority 2");
        Thread thread3 = new Thread(new Worker(AnsiColors.ANSI_GREEN), "Priority 3");
        Thread thread4 = new Thread(new Worker(AnsiColors.ANSI_PURPLE), "Priority 4");
        Thread thread5 = new Thread(new Worker(AnsiColors.ANSI_BLUE), "Priority 5");

        thread1.setPriority(1);
        thread2.setPriority(2);
        thread3.setPriority(3);
        thread4.setPriority(4);
        thread5.setPriority(5);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

    }

    private static class Worker implements Runnable {
        private int count = 1;
        private String color;

        Worker(String color) {
            this.color = color;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                lock.lock();
                try {
                    System.out.format(color + "%s : Count = %d\n", Thread.currentThread().getName(), count++);
                } finally {
                    lock.unlock();
                }

            }
        }
    }
}