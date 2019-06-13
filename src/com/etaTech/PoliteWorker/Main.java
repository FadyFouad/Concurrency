package com.etaTech.PoliteWorker;

/****************************************************
 *** Created by Fady Fouad on 6/13/2019 at 16:41.***
 ***************************************************/
public class Main {
    public static void main(String[] args) {
        Worker worker1 = new Worker("worker 1",true);
        Worker worker2 = new Worker("worker 2",true);
        SharedRes sharedRes = new SharedRes(worker1);

        new Thread(() -> worker1.work(sharedRes,worker2)).start();
        new Thread(() -> worker2.work(sharedRes,worker1)).start();


    }
}
