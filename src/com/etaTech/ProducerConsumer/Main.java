package com.etaTech.ProducerConsumer;

import com.etaTech.AnsiColors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import static com.etaTech.ProducerConsumer.Main.EOF;

/****************************************************
 *** Created by Fady Fouad on 6/12/2019 at 11:33.***
 ***************************************************/
public class Main {
    public static final String EOF = "EOF";

    public static void main(String[] args) {
        List<String> bufer = new ArrayList<>();
        ReentrantLock lock = new ReentrantLock();
        Producer producer = new Producer(bufer, AnsiColors.ANSI_GREEN, lock);
        Consumer consumer = new Consumer(bufer, AnsiColors.ANSI_RED, lock);
        Consumer consumer2 = new Consumer(bufer, AnsiColors.ANSI_YELLOW, lock);
        new Thread(producer).start();
        new Thread(consumer).start();
        new Thread(consumer2).start();

    }
}

class Producer implements Runnable {

    private List<String> buffer;
    private String color;
    private ReentrantLock lock;

    public Producer(List<String> buffer, String color, ReentrantLock lock) {
        this.buffer = buffer;
        this.color = color;
        this.lock = lock;
    }

    @Override
    public void run() {
        Random random = new Random();
        String[] nums = {"1", "2", "3", "4",};
        for (String num :
                nums) {
//            synchronized (buffer) {
            try {
                System.out.println(color + "Adding.." + num+ " --> " +Thread.currentThread().getName());
                lock.lock();
                buffer.add(num);
                lock.unlock();

                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        }
        System.out.println(AnsiColors.ANSI_WHITE + "Buffer Adding EOF"+ " --> " +Thread.currentThread().getName());
        lock.lock();
        buffer.add("EOF");
        lock.unlock();
    }
}


class Consumer implements Runnable {
    private List<String> buffer;
    private String color;
    private ReentrantLock lock;

    public Consumer(List<String> buffer, String color, ReentrantLock lock) {
        this.buffer = buffer;
        this.color = color;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();
            if (buffer.isEmpty()) {
                lock.unlock();
                continue;
            }
            if (buffer.get(0).equals(EOF)) {
                System.out.println(color + "Exiting"+ " --> " +Thread.currentThread().getName());
                lock.unlock();
                break;
            } else {
                System.out.println(color + " Removed " + buffer.remove(0)+ " --> " +Thread.currentThread().getName());
            }
            lock.unlock();
        }
    }
}