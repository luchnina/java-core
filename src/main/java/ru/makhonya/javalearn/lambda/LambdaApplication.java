package ru.makhonya.javalearn.lambda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaApplication {

    private static final Supplier<Integer> integerGenerator = () -> new Random().nextInt();
    private static final Predicate<Integer> isEven = x -> x % 2 == 0;
    private static final Function<Integer, String> evenFunction = x -> isEven.test(x) ? "четное" : "нечетное";

    void main() {
        int numberRandom = integerGenerator.get();
        Supplier<Integer> increment = incrementSupplier();

        loggerConsumer().accept(evenFunction.apply(numberRandom));
        loggerConsumer().accept(increment.get().toString());
        loggerConsumer().accept(increment.get().toString());
    }

    public static Supplier<Integer> incrementSupplier() {
        int[] count = {0};
        return () -> count[0]++;
    }

    public static Consumer<String> loggerConsumer() {
        Logger log = LoggerFactory.getLogger(LambdaApplication.class);
        return obj -> log.info("{}", obj);
    }
}
