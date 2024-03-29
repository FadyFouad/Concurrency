package com.etaTech.ProducerConsumer;

import com.etaTech.AnsiColors;

import java.util.Random;
import java.util.concurrent.*;

import static com.etaTech.ProducerConsumer.Main.EOF;

/****************************************************
 *** Created by Fady Fouad on 6/12/2019 at 11:33.***
 ***************************************************/
public class Main {
    static final String EOF = "EOF";

    public static void main(String[] args) {
        ArrayBlockingQueue<String> bufer = new ArrayBlockingQueue<>(4);

        ExecutorService service = Executors.newFixedThreadPool(3);
        Producer producer = new Producer(bufer, AnsiColors.ANSI_GREEN);
        Consumer consumer = new Consumer(bufer, AnsiColors.ANSI_RED);
        Consumer consumer2 = new Consumer(bufer, AnsiColors.ANSI_YELLOW);
        service.execute(producer);
        service.execute(consumer);
        service.execute(consumer2);
        Future<String> future = service.submit(() -> {
            System.out.println(AnsiColors.ANSI_PURPLE + "Callable Class");
            return "Callable Method return this";
        });
        try {
            System.out.println("try block : " + future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }
}

class Producer implements Runnable {

    private ArrayBlockingQueue<String> buffer;
    private String color;

    Producer(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        Random random = new Random();
        String[] nums = {"1", "2", "3", "4",};
        for (String num :
                nums) {
            try {
                System.out.println(color + "Adding.." + num + " --> " + Thread.currentThread().getName());
                buffer.put(num);
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(AnsiColors.ANSI_WHITE + "Buffer Adding EOF" + " --> " + Thread.currentThread().getName());
        try {
            buffer.put("EOF");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class Consumer implements Runnable {
    private final ArrayBlockingQueue<String> buffer;
    private String color;

    Consumer(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (buffer) {
                try {
                    if (buffer.isEmpty()) {
                        continue;
                    }
                    if (buffer.peek().equals(EOF)) {
                        System.out.println(color + "Exiting" + " --> " + Thread.currentThread().getName());
                        break;
                    } else {
                        System.out.println(color + " Removed " + buffer.take() + " --> " + Thread.currentThread().getName());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}