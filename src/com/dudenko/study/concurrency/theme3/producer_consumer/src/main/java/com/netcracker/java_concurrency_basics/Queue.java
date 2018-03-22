package com.dudenko.study.concurrency.theme3.producer_consumer.src.main.java.com.netcracker.java_concurrency_basics;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

class Queue<E> {
    private final LinkedList<E> queue = new LinkedList<>();

    E take() throws Exception {
        synchronized (queue) {
            while (queue.isEmpty()) {
                queue.wait();
            }

            return queue.pollFirst();
        }
    }

    void offer(E e) throws Exception {
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
