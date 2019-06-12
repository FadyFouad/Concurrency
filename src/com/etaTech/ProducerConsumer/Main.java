package com.etaTech.ProducerConsumer;

import com.etaTech.AnsiColors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.etaTech.ProducerConsumer.Main.EOF;

/****************************************************
 *** Created by Fady Fouad on 6/12/2019 at 11:33.***
 ***************************************************/
public class Main {
    public static final String EOF = "EOF";

    public static void main(String[] args) {
        List<String> bufer = new ArrayList<>();
        Producer producer = new Producer(bufer, AnsiColors.ANSI_GREEN);
        Consumer consumer = new Consumer(bufer, AnsiColors.ANSI_RED);
        Consumer consumer2 = new Consumer(bufer, AnsiColors.ANSI_YELLOW);
        new Thread(producer).start();
        new Thread(consumer).start();
        new Thread(consumer2).start();

    }
}

class Producer implements Runnable {

    private List<String> buffer;
    private String color;

    public Producer(List<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        Random random = new Random();
        String[] nums = {"1", "2", "3", "4",};
        for (String num :
                nums) {
//            synchronized (buffer) {
            try {
                System.out.println(color + "Adding.." + num);
                synchronized (buffer) {
                    buffer.add(num);
                }
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        }
        System.out.println(AnsiColors.ANSI_WHITE + "Buffer Adding EOF");
        synchronized (buffer) {
            buffer.add("EOF");
        }
    }
}

class Consumer implements Runnable {
    private List<String> buffer;
    private String color;

    public Consumer(List<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (buffer) {
                if (buffer.isEmpty()) {
                    continue;
                }
                if (buffer.get(0).equals(EOF)) {
                    System.out.println(color + "Exiting");
                    break;
                } else {
                    System.out.println(color + " Removed " + buffer.remove(0));
                }
            }
        }
    }
}