package cuncurrency;

import java.util.concurrent.*;

public class ExecutorServiceTest {

    public static void main(String... args) throws ExecutionException {

        try {
            ExecutorService executorService = Executors.newFixedThreadPool(2);
            executorService.submit(() -> {
                for (int i = 0; i < 2; i++) {
                    System.out.println("thread1");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            Future<String> thread2 = executorService.submit(() -> {
                for (int i = 0; i < 2; i++) {
                    System.out.println("thread2");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return "result";
            });

//            System.out.println("result " + thread2.get());
            System.out.println("continue");

            boolean termination = executorService.awaitTermination(7, TimeUnit.SECONDS);
            System.out.println("terminate + " + termination);
            executorService.shutdown();
        } catch (InterruptedException e) {

        }
    }

    protected void finalize() {

    }
}
