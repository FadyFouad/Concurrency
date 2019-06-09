package com.etaTech.Threads;

import com.etaTech.Threads.MyThread;

import static com.etaTech.AnsiColors.ANSI_GREEN;
import static com.etaTech.AnsiColors.ANSI_RED;

public class Main {

    public static void main(String[] args) {

        System.out.println(ANSI_RED+"The Main Thread");
        Thread myThread = new MyThread();
        myThread.start();

        new Thread(){
            public void run(){
                System.out.println(ANSI_GREEN+"This is anonymous class thread");
            }
        }.start();

        System.out.println(ANSI_RED+"The Main Thread again");

    }
}
