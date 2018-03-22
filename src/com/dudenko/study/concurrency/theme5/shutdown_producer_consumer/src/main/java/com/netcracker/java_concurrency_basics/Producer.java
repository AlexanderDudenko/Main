package com.dudenko.study.concurrency.theme5.shutdown_producer_consumer.src.main.java.com.netcracker.java_concurrency_basics;

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

        for (int i = 0; i < msgNum; ++i) {
            //TODO
            if (t.isInterrupted()) {
                System.out.println("Producer " + id + " interrupted!");
                break;
            }

            String msg = "PROD" + id + "-" + i;
            try {
                Thread.sleep(50);

                queue.offer(msg);

                System.out.println("Sent message: " + msg);

            } catch (InterruptedException e) {
                System.out.println("Producer " + id + " interrupted! (exception)");
                break;
            }
        }

    }

    void start() {
        t.start();
    }

    void shutdown() {
        t.interrupt();
        System.out.println("Producer " + id + " has been shutdowned");
    }
}
