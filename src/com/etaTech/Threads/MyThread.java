package com.etaTech.Threads;

import static com.etaTech.AnsiColors.ANSI_YELLOW;

/****************************************************
 *** Created by Fady Fouad on 6/9/2019 at 22:31.***
 ***************************************************/
public class MyThread extends Thread {
    @Override
    public void run() {
//        super.run();
        System.out.println(ANSI_YELLOW+"This is my Thread");
    }
}