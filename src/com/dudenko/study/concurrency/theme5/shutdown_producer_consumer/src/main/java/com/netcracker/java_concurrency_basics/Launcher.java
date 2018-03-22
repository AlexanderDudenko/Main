package com.dudenko.study.concurrency.theme5.shutdown_producer_consumer.src.main.java.com.netcracker.java_concurrency_basics;

import java.util.ArrayList;
import java.util.List;

public class Launcher {
    private static final int CONSUMERS_NUM = 2;
    private static final int PRODUCER_NUM = 3;
    private static final int MSG_NUM = 100;

    public static void main(String[] args) {
        Queue<String> queue = new Queue<>();

        List<Consumer> consumers = new ArrayList<>(CONSUMERS_NUM);

        for (int i = 0; i < CONSUMERS_NUM; i++) {
            consumers.add(new Consumer(queue, i));
        }

        List<Producer> producers = new ArrayList<>(PRODUCER_NUM);

        for (int i = 0; i < PRODUCER_NUM; i++) {
            producers.add(new Producer(queue, i, MSG_NUM));
        }

        // Start producers and consumers.
        for (Consumer consumer : consumers) {
            consumer.start();
        }

        for (Producer producer : producers) {
            producer.start();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Producer producer : producers) {
            producer.shutdown();
        }

        for (Consumer consumer : consumers) {
            consumer.shutdown();
        }

        // No shutdown is needed.
    }
}
