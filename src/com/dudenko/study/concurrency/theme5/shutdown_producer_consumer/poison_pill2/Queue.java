package com.dudenko.study.concurrency.theme5.shutdown_producer_consumer.poison_pill2;

import java.util.LinkedList;
import java.util.Objects;

class Queue<E> {
    private final LinkedList<E> queue = new LinkedList<>();

    E take() throws InterruptedException {
        synchronized (queue) {
            while (queue.isEmpty()) {
                queue.wait();
            }

            return queue.pollFirst();
        }
    }

    void offer(E e) {
        Objects.requireNonNull(e, "Ready");

        synchronized (queue) {
            int size = queue.size();

            queue.add(e);

            if (size == 0) {
                queue.notifyAll();
            }
        }
    }


}
