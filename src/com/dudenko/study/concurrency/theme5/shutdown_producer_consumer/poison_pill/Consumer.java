package com.dudenko.study.concurrency.theme5.shutdown_producer_consumer.poison_pill;

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
            while (true) {
                queueMsg = queue.take();
                if (queueMsg.equals(Producer.POISON_PILL_MSG)) {
                    System.out.println("CONS" + id + " has been shutdown.");
                    break;
                }
                System.out.println("CONS" + id + " received message: " + queueMsg);
            }
        } catch (InterruptedException e) {
            System.out.println("CONS" + id + " cause of exception: " + e.getMessage());
        }
    }

    void start() {
        t.start();
    }

    void shutdown() {

    }
}
