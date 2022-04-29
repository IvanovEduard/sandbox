package tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

public class ExercisesGenerator {

    private static final Random random = new Random();
    private static final String PLUS_OPERATOR = "+";
    private static final String MINUS_OPERATOR = "-";

    public static void main(String... args) {
        System.out.println("Start exercises!");
        List<String> setResults = new ArrayList<>();
        int result;
        int i = 0;
        for (int j = 0; j < 10;) {

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
//        Arrays.sort(strings, Comparator.reverseOrder().);
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
}
