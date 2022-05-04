package interviewtasks;

import java.math.BigInteger;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.IntStream;

public class TaskZagnitkoFactorialTest {

    public static void main(String[] args) {
        Function<Integer, Optional<BigInteger>> result = (value) ->
             IntStream.rangeClosed(1, value).boxed()
                    .map(BigInteger::valueOf)
                    .reduce(BigInteger::multiply);

        Optional<BigInteger> apply = result.apply(100);
        System.out.println(apply.get());
    }
}
