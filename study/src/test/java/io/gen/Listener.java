package io.gen;

import io.gen.study.Job;
import org.junit.jupiter.api.Test;

public class Listener {

    @Test
    public void test(){
        Thread t = new Thread(new Job());
        t.start();
    }
}
