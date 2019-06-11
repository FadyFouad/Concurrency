package com.etaTech.Messages;

import java.util.Random;

/****************************************************
 *** Created by Fady Fouad on 6/10/2019 at 23:25.***
 ***************************************************/
public class Main {
    public static void main(String[] args) {
        System.out.println("\n------------------------Messages---------------------------\n");

        Message message =new Message();
        (new Thread(new Writer(message))).start();
        (new Thread(new Reader(message))).start();

    }
}

class Message {
    private String message;
    private boolean empty = true;

    public synchronized String read() {
        while (empty) {

        }
        empty = true;
        return message;
    }

    public synchronized void write(String message) {
        while (!empty) {

        }
        empty = false;
        this.message = message;
    }


}

class Writer implements Runnable {
    private Message message;

    public Writer(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        String[] messages = {"m1", "m2", "m3", "m4", "m5", "m6", "m7"};

        Random random = new Random();

        for (int i = 0; i < messages.length; i++) {
            message.write(messages[i]);
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        message.write("Finished");
    }

}

class Reader implements Runnable{

    private Message message ;

    public Reader(Message message) {
        this.message = message;
    }

    @Override
    public void run() {

        Random random = new Random();
        for (String latestMess = message.read();!latestMess.equals("Finished");latestMess=message.read()){
            System.out.println(latestMess);
            try{
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}