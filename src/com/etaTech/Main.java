package com.etaTech;

import com.etaTech.Threads.MyThread;

public class Main {

    public static void main(String[] args) {

        System.out.println("The Main Thread");
        MyThread myThread = new MyThread();
        myThread.start();
    }
}
