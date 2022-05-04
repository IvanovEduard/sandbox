package interviewtasks;

import lombok.SneakyThrows;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Task:
 * - input for the program is the number of exercises (e.g. 10)
 * - the program generates 2 random non-negative integer numbers up to 100 and an operation (+/-) ... e.g. 24+37
 * - the user should enter the result ... i.e. 61
 * - the program generates 10 such exercises (input) if the result is not correct or the result was entered later than in 12 seconds, the program does not consider such an exercise and generates another one instead of it.
 * the child who is trained by the program is familiar with the numbers from 0 to 100 and with operations + and -
 */
public class ExercisesGenerator {

    private static final Random random = new Random();
    private static final String PLUS_OPERATOR = "+";
    private static final String MINUS_OPERATOR = "-";

    @SneakyThrows
    public static void main(String... args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

         Runnable threadBest = ExercisesGenerator::bestApproach;

        long startTime = System.currentTimeMillis();
        Future<?> submit = executorService.submit(threadBest);
        long endTime = System.currentTimeMillis();

        Object o = submit.get();
        if (submit.isDone()) {
            executorService.submit(threadBest);
        }
        executorService.shutdown();
    }

    private static void badApproach() {
        System.out.println("Start exercises!");
        List<String> setResults = new ArrayList<>();
        int result;
        int i = 0;
        for (int j = 0; j < 10; ) {

            int numberOne = random.nextInt(100) + 1;
            int numberTwo = random.nextInt(100) + 1;

            boolean isPlusOperation = isPlusOperation();

            if (isPlusOperation) {
                result = numberOne + numberTwo;
            } else {
                result = numberOne - numberTwo;
            }
            if (result >= 0 && result <= 100) {
                i++;
                j++;
                System.out.println("Solve the exercise #" + i);

                String exercise = buildExercise(numberOne, numberTwo, getOperator(isPlusOperation), i);
                System.out.println(exercise);

                long startTime = System.currentTimeMillis();
                Scanner scanner = new Scanner(System.in);
                long answer = scanner.nextLong();
                long endTime = System.currentTimeMillis();

                if (endTime - startTime > 12000) {
                    setResults.add(exercise + " - Time exceeded No answer");
                } else if (answer == result) {
                    String replace = exercise.replace("?", String.valueOf(answer));
                    setResults.add(replace + " - Correct");
                } else {
                    String replace = exercise.replace("?", String.valueOf(answer));
                    setResults.add(replace + " - Incorrect");
                }
            }
        }
        char[] chars = "asd".toCharArray();
        String[] strings = Stream.of(chars).map(String::new).toArray(String[]::new);
        Arrays.sort(strings, Comparator.reverseOrder());

        System.out.println("All exercises is finished!");
        System.out.println("Yor results:");
        setResults.forEach(System.out::println);
    }

    private static void bestApproach() {
        System.out.println("Start exercises!");
        List<String> setResults = new ArrayList<>();
        IntStream.rangeClosed(1, 3)
                .forEach(index -> {
                    Exercise exercise = generateExercise();
                    int resultMain = PLUS_OPERATOR.equals(exercise.getOperator()) ? exercise.getFirstNum() + exercise.getSecondNum()
                            : exercise.getFirstNum() - exercise.getSecondNum();
                    System.out.println("Solve the exercise #" + index);

                    String exerciseStr = buildExercise(exercise.getFirstNum(), exercise.getSecondNum(), exercise.getOperator(), index);
                    System.out.println(exerciseStr);

                    long startTime = System.currentTimeMillis();
                    Scanner scanner = new Scanner(System.in);
                    long answer = scanner.nextLong();
                    long endTime = System.currentTimeMillis();

                    if (endTime - startTime > 12000) {
                        setResults.add(exerciseStr + " - Time exceeded No answer. The answer is - " + resultMain);
                    } else if (answer == resultMain) {
                        String replace = exerciseStr.replace("?", String.valueOf(answer));
                        setResults.add(replace + " - Correct");
                    } else {
                        String replace = exerciseStr.replace("?", String.valueOf(answer));
                        setResults.add(replace + " - Incorrect. Correc is - " + resultMain);
                    }
                });

        System.out.println("All exercises is finished!");
        System.out.println("Yor results:");
        setResults.forEach(System.out::println);
    }

    private static String getOperator(boolean isPlusOperation) {
        return isPlusOperation ? PLUS_OPERATOR : MINUS_OPERATOR;
    }

    private static boolean isPlusOperation() {
        return random.nextBoolean();
    }

    private static String buildExercise(final int numberOne, final int numberTwo, final String operator, final int exerciseNumber) {
        return "#" + exerciseNumber + " " + numberOne + " " + operator + " " + numberTwo + " = ?";
    }

    private static Exercise generateExercise() {
        boolean isPlusOperation = isPlusOperation();
        int numberOne = random.nextInt(100) + 1;
        if (isPlusOperation) {
            int numberTwo = numberOne == 100 ? 0 : random.nextInt(100 - numberOne) + 1;
            return new Exercise(numberOne, numberTwo, PLUS_OPERATOR);
        }
        int numberTwo = random.nextInt(100) + 1;
        return numberOne > numberTwo ? new Exercise(numberOne, numberTwo, MINUS_OPERATOR) : new Exercise(numberTwo, numberOne, MINUS_OPERATOR);
    }

    private static class Exercise {
        private final int firstNum;
        private final int secondNum;
        private final String operator;

        public Exercise(int firstNum, int secondNum, String operator) {
            this.firstNum = firstNum;
            this.secondNum = secondNum;
            this.operator = operator;
        }

        public int getFirstNum() {
            return firstNum;
        }

        public int getSecondNum() {
            return secondNum;
        }

        public String getOperator() {
            return operator;
        }
    }
}
