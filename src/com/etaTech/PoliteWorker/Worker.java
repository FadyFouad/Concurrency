package com.etaTech.PoliteWorker;

/****************************************************
 *** Created by Fady Fouad on 6/13/2019 at 16:42.***
 ***************************************************/
public class Worker {
    private String name;
    private boolean isActive;

    public Worker(String name, boolean isActive) {
        this.name = name;
        this.isActive = isActive;
    }

    public synchronized void work (SharedRes sharedRes,Worker worker) {
        while (isActive){
            if (sharedRes.getOwner()!=this){
                try {
                    wait(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            if (worker.isActive){
                System.out.println(getName()+" : give the res to the other worker "+worker.getName());
                sharedRes.setOwner(worker);
                continue;
            }
            System.out.println(getName()+" : working ");
            isActive = false;
            sharedRes.setOwner(worker);
        }
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return isActive;
    }
}