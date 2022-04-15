package op;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.cassandra.DataCassandraTest;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Eduard Ivanov on 6/29/21
 */

public class DogTest {

    @Test
    public void test1() {
        if (null instanceof Object) {
            System.out.println("");
        }
        Optional<String> o = Optional.ofNullable("null");
        Predicate<String> predicate = getStringPredicate();
        o.filter(predicate);


        Collections.unmodifiableMap(new HashMap<>());
        List<String> strings = List.of("a,b,c", "a");
        List<String> collect = strings.stream().flatMap(s -> Arrays.stream(s.split(","))).peek(System.out::println).collect(Collectors.toList());
        System.out.println(collect);


        List<Integer> ints = List.of(10, 3, 3);
        Integer integer = ints.stream().reduce(Integer::sum).get();
        OptionalDouble average = IntStream.of(ints.stream().mapToInt(i -> i).toArray()).average();
        System.out.println(average);

        List<Integer> integers = List.of(new Integer[]{1, 2, 3});
        System.out.println(integers);

        List<String> reduce = List.of("sas", "sd", "aaa");
        String result = reduce.stream().reduce((s, s2) -> s + s2).get();
        System.out.println(result);

        List<String> collect1 = reduce.stream().skip(1).limit(3).collect(Collectors.toList());
        System.out.println(collect1);
        String collect2 = reduce.stream().skip(1).limit(3).collect(Collectors.joining(","));
        System.out.println(collect2);

        StringJoiner stringJoiner = new StringJoiner(",", "(", ")");
        stringJoiner.add("ass").add("ssss");
        System.out.println(stringJoiner);

    }

    private Predicate<String> getStringPredicate() {
        return v -> v.equals("null");
    }


    @Test
    public void test2() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        Callable<String> task = () -> "thread1";
        Callable<String> task1 = () -> "thread2";
        Runnable task3 = () -> System.out.println("Vasya");
        List<Future<String>> futures = executorService.invokeAll(List.of(task, task1));
        Future<String> submit = executorService.submit(task);
        Future<?> submit1 = executorService.submit(task3);

        futures.forEach(f -> {
            try {
                System.out.println(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
    }


    @Test
    public void test3() throws Exception {
        try {
            throw new Exception();
        } catch (Exception e) {
            throw new Exception();
        } finally {
            System.out.println("finally");
        }
    }


    @Test
    public void testStreamMap() throws InterruptedException {
        User user = new User();
        user.setAge(12);
        user.setName("Dasha");
        User user1 = new User();
        user1.setAge(13);
        user1.setName("Sveta");
        User user2 = new User();
        user2.setAge(13);
        user2.setName("Oksana");
        User user3 = new User();
        user3.setAge(13);
        user3.setName("Oksana");


        List<User> values = new ArrayList<>(List.of(user, user1, user2, user3));
        values.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        Map<Integer, List<User>> collect = values.stream().collect(Collectors.groupingBy(User::getAge));
        Map<Integer, String> collect2 = values.stream().collect(Collectors.groupingBy(User::getAge, Collectors.mapping(User::getName, Collectors.joining())));

        Map<Integer, String> collect1 = values.stream().collect(Collectors.toMap(User::getAge, User::getName, (v1, v2) -> v1));

        Map<Integer, Long> collect3 = values.stream().collect(Collectors.groupingBy(User::getAge, Collectors.counting()));
        Map<String, IntSummaryStatistics> collect4 = values.stream().collect(Collectors.groupingBy(User::getName, Collectors.summarizingInt(User::getAge)));
        Map<String, Integer> collect5 = values.stream().collect(Collectors.groupingBy(User::getName, Collectors.summingInt(User::getAge)));
        System.out.println(collect);

    }

    public class User {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            User user = (User) o;

            return new EqualsBuilder().append(age, user.age).append(name, user.name).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(name).append(age).toHashCode();
        }
    }
}
