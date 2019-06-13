package com.etaTech.DeadLock;

import com.etaTech.AnsiColors;

/****************************************************
 *** Created by Fady Fouad on 6/13/2019 at 11:40.***
 ***************************************************/
public class Main {

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {

        new Thread1().start();
        new Thread2().start();

    }

    private static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (lock1) {
                System.out.println(AnsiColors.ANSI_PURPLE + "Thread 1 : Lock 1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(AnsiColors.ANSI_RED + "Thread 1 wait for lock 2");
                synchronized (lock2) {
                    System.out.println(AnsiColors.ANSI_PURPLE + "Thread 1 : lock 1 & lock 2");
                }
                System.out.println(AnsiColors.ANSI_GREEN + "Thread 1 release lock 2");
            }
            System.out.println("Thread 1 release lock 1 -> Exiting ");
        }
    }

    private static class Thread2 extends Thread {
        @Override
        public void run() {
//            synchronized (lock2) {
//                System.out.println(AnsiColors.ANSI_PURPLE + "Thread 2 : Lock 2");
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(AnsiColors.ANSI_RED + "Thread 2 wait for lock 2");
//                synchronized (lock1) {
//                    System.out.println(AnsiColors.ANSI_PURPLE + "Thread 2 : lock 2 & lock 1");
//                }
//                System.out.println(AnsiColors.ANSI_GREEN + "Thread 2 release lock 1");
//            }
//            System.out.println("Thread 2 release lock 2 -> Exiting ");
//        }
            //Same Order
        synchronized (lock1) {
                System.out.println(AnsiColors.ANSI_PURPLE + "Thread 2 : Lock 1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(AnsiColors.ANSI_RED + "Thread 2 wait for lock 1");
                synchronized (lock2) {
                    System.out.println(AnsiColors.ANSI_PURPLE + "Thread 2 : lock 1 & lock 2");
                }
                System.out.println(AnsiColors.ANSI_GREEN + "Thread 2 release lock 2");
            }
            System.out.println("Thread 2 release lock 1 -> Exiting ");
        }
    }
}