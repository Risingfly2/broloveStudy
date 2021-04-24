package io.gen;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> {
                System.out.println("Current thread: " + Thread.currentThread().getName() + " begin to await");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("Current thread: " + Thread.currentThread().getName() + " begin to running");
            });
        }
        cyclicBarrier.reset();
        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    cyclicBarrier.await();
                    System.out.println(" runnnnnnn");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
    }
}
