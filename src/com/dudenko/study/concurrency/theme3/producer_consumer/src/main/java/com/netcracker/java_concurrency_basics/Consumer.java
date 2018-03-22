package com.dudenko.study.concurrency.theme3.producer_consumer.src.main.java.com.netcracker.java_concurrency_basics;

class Consumer {
    private final Queue<String> queue;
    private final int id;
    private final Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            doJob();
        }
    });

    Consumer(Queue<String> queue, int id) {
        this.queue = queue;
        this.id = id;
    }

    private void doJob() {
        String queueMsg;
        while (true) {
            try {
                queueMsg = queue.take();
                // Replace <msg> with real msg.
                System.out.println("CONS" + id + " received message: " + queueMsg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    void start() {
        t.start();
    }
}
