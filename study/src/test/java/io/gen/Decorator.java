package io.gen;

import io.gen.desigin.pattern.decorator.A;
import io.gen.desigin.pattern.decorator.ADecorator;
import org.junit.jupiter.api.Test;

public class Decorator {

    @Test
    public void test(){
        ADecorator aDecorator = new ADecorator(new A());
        aDecorator.f();
    }
}
