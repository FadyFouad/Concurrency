package com.etaTech.Threads;

import com.etaTech.Threads.MyThread;

import static com.etaTech.AnsiColors.*;

public class Main {

    public static void main(String[] args) {

        System.out.println(ANSI_RED+"The Main Thread -> "+Thread.currentThread().getName());

        Thread myThread = new MyThread();
//        myThread.setName("Fady Thread");
        myThread.start();
//        myThread.run(); run() run main thread
//        myThread.interrupt();

        new Thread(){
            public void run(){
                System.out.println(ANSI_GREEN+"This is anonymous class thread -> "+Thread.currentThread().getName());
                try {
                    myThread.join(1000);
                    System.out.println(ANSI_GREEN+"This is anonymous class thread Running again(My thread done || time out) -> "+Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    System.out.println(ANSI_GREEN+e.getMessage());

                }
            }
        }.start();

        System.out.println(ANSI_RED+"The Main Thread again -> "+Thread.currentThread().getName());

        Thread runnable = new Thread(new RunnableClass());
        runnable.start();

        Thread runnable2 = new Thread(new RunnableClass(){
            @Override
            public void run() {
                System.out.println(ANSI_BLUE+"This is anonymous runnable thread -> "+Thread.currentThread().getName());
            }
        });
        runnable2.start();

    }
}
