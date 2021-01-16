package io.gen;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import static java.util.concurrent.TimeUnit.SECONDS;

public class ScheduleTest {

    @Test
    public void scheduleTest()throws Exception{
        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(10);
        for (;;){
            ScheduledFuture future = service.schedule(() -> System.out.println("handsome boy"), 5L,SECONDS);
            future.get();
        }

    }
}
