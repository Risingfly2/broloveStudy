package io.gen.study;

import java.util.function.Supplier;

public class Job implements NotifyListener, Runnable{
    public Job() {
    }

    @Override
    public void onSuccess() {

        System.out.println("onsucess callback");
    }

    @Override
    public void onFailure() {

        System.out.println("onfailer callback");
    }

    public void doSomething(Supplier<String> supplier, FuckListtener listener){
        System.out.println(supplier.get());
        listener.fuck("shit");
    }
    @Override
    public void run() {

        try {
            for (int i = 0; i < 10; i++) {
                doSomething(() -> {
                    return "genge: " + Thread.currentThread().getName() + " " + "nb";
                }, (shit) -> {
                    for (int j = 0; j < 3; j++) {
                        System.out.println("fuck listener" + shit);
                    }
                });
            }
            onSuccess();
        } catch (Exception e) {
            onFailure();
        }
    }
}
