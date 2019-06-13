package com.etaTech.PoliteWorker;

/****************************************************
 *** Created by Fady Fouad on 6/13/2019 at 16:41.***
 ***************************************************/
public class SharedRes {
    private Worker owner ;

    public SharedRes(Worker owner) {
        this.owner = owner;
    }

    Worker getOwner() {
        return owner;
    }

    void setOwner(Worker owner) {
        this.owner = owner;
    }
}