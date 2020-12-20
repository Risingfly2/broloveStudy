package io.gen.desigin.pattern.decorator;

/**
 * Decorator and decoratored class should implements the same interface,
 * the decorator strength the class
 */
public class ADecorator implements IA{

    private IA a;
    public ADecorator(IA a){
        this.a = a;
    }
    @Override
    public void f() {
        System.out.println("strength nb");
        a.f();
        System.out.println("nbnbnbnbnbnbnbnbnnbnbnb");
    }
}
