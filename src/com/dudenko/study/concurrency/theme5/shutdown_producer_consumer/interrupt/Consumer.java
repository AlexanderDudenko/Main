package com.dudenko.study.concurrency.theme5.shutdown_producer_consumer.interrupt;

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
        try {
            while (!t.isInterrupted()) {
                queueMsg = queue.take();
                System.out.println("CONS" + id + " received message: " + queueMsg);
            }
        } catch (InterruptedException e) {
            System.out.println("CONS" + id + " interrupted!");
//            e.fillInStackTrace();
        }
    }

    void start() {
        t.start();
    }

    void shutdown() {
        t.interrupt();
        System.out.println("Consumer " + id + " has been shutdowned");
    }
}
