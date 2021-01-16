package io.gen;

import io.gen.study.Job;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

public class Listener {
    public static final Logger log = LogManager.getLogger(Listener.class);
    @Test
    public void test(){
        Thread t = new Thread(new Job());
        log.info("come on baby");
        t.start();
    }
}
