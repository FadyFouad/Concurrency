package com.etaTech.Counter;

import com.etaTech.AnsiColors;

/****************************************************
 *** Created by Fady Fouad on 6/10/2019 at 13:17.***
 ***************************************************/
public class Main {
    public static void main(String[] args) {
        System.out.println("------------------------Counter---------------------------");
        CountDown countDown0 = new CountDown();
//        CountDown countDown1 = new CountDown();//No Thread Interference (Not efficient)
        CountDownThread thread1 = new CountDownThread(countDown0);
        CountDownThread thread2 = new CountDownThread(countDown0);
//        thread1.getName();
//        thread2.getName();
        thread1.start();
        thread2.start();
    }
}

class CountDown {
    private int i; //instant var stored at heap but local stored at  stack

    public /*synchronized*/ void doCountDown() {//synchronized methods preventing thread interference and memory consistency errors
        String color;
        switch (Thread.currentThread().getName()) {
            case "Thread-0":
                color = AnsiColors.ANSI_GREEN;
                break;
            case "Thread-1":
                color = AnsiColors.ANSI_RED;
                break;
            default:
                color = AnsiColors.ANSI_RESET;
        }
        synchronized (this) {
            for (i = 10; i > 0; i--) {
                System.out.println(color + Thread.currentThread().getName() + " # " + i);
            }
        }
    }
}

class CountDownThread extends Thread {
    private CountDown countDown;

    public CountDownThread(CountDown countDown) {
        this.countDown = countDown;
    }

    @Override
    public void run() {
        countDown.doCountDown();
    }
}