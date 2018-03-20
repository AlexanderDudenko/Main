package com.dudenko.study.concurrency.theme3.producer_consumer.src.main.java.com.netcracker.java_concurrency_basics;

class Producer {
    private final Queue<String> queue;
    private final int id;
    private final int msgNum;
    private final Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            doJob();
        }
    });

    Producer(Queue<String> queue, int id, int msgNum) {
        this.queue = queue;
        this.id = id;
        this.msgNum = msgNum;
    }

    private void doJob() {
        // Implement.

        // Replace <id> with real id.
        String msg = "PROD" + id + "-" + "<id>";

        System.out.println("Sent message: " + msg);
    }

    void start() {
        // Implement.
    }
}
