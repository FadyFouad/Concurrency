package com.etaTech.Threads;

import static com.etaTech.AnsiColors.ANSI_BLUE;

/****************************************************
 *** Created by Fady Fouad on 6/9/2019 at 22:55.***
 ***************************************************/
public class RunnableClass implements Runnable {
    @Override
    public void run() {
        System.out.println(ANSI_BLUE+"This is Runnable Class -> "+Thread.currentThread().getName());
    }
}