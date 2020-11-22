package io.gen;

import io.gen.decorator.A;
import io.gen.decorator.ADecorator;
import org.junit.jupiter.api.Test;

public class Decorator {

    @Test
    public void test(){
        ADecorator aDecorator = new ADecorator(new A());
        aDecorator.f();
    }
}
