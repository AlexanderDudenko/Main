package com.dudenko.study.concurrency.theme5.shutdown_producer_consumer.poison_pill;

class Producer {
    private final Queue<String> queue;
    private final int id;
    private final int msgNum;
    private boolean interrupt;
    public static final String POISON_PILL_MSG = "I GOT A POISON!";
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
        for (int i = 0; i < msgNum; ++i) {
            if (interrupt) {
                System.out.println("Producer " + id + " interrupted!");
                break;
            }

            String msg = "PROD" + id + "-" + i;
            try {
                Thread.sleep(50);

                queue.offer(msg);

                System.out.println("Sent message: " + msg);

            } catch (InterruptedException e) {
//                System.out.println("Producer " + id + " interrupted! (exception)");
//                break;
            }
        }

    }

    void start() {
        t.start();
    }

    void shutdown() {
        queue.offer(POISON_PILL_MSG);
        interrupt = true;
    }
}
