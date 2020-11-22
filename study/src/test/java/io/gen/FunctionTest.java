package io.gen;

import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctionTest {

    @Test
    public void test(){
        Function<Integer, Integer> A = i -> i + 1;
        Function<Integer, Integer> B = i -> i * i;

        System.out.println(B.apply(A.apply(5)));
        System.out.println(B.compose(A).apply(5));
        System.out.println(B.andThen(A).apply(5));
        System.out.println(B.andThen(B).compose(A).compose(A).andThen(B).apply(10));
        int res = compute(3,5, (x, y) -> x + y);
        int res1 = compute(3,5, (x, y) -> x * y);
        System.out.println(res1);

        int res2 = compute(3,5,(x,y) -> x + y, x -> x * x );
        System.out.println(res2);
    }

    public int compute(int a, int b, BiFunction<Integer, Integer, Integer> biFunction){
        return biFunction.apply(a,b);
    }

    public int compute(int a, int b, BiFunction<Integer, Integer, Integer> biFunction, Function<Integer, Integer> function){
        return biFunction.andThen(function).apply(a,b);
    }
}
