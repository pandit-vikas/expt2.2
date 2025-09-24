//Write a Java program to calculate the sum of a list of integers using autoboxing and unboxing. Include methods to parse strings into their respective wrapper classes (e.g., Integer.parseInt()).
import java.util.ArrayList;
import java.util.List;

public class AutoboxingUnboxing {

    // Method to parse strings into Integer wrapper objects
    public static List<Integer> parseStringsToIntegers(String[] numberStrings) {
        List<Integer> numbers = new ArrayList<>();
        for (String str : numberStrings) {
            // parse string to int (primitive), autobox into Integer
            Integer num = Integer.parseInt(str);
            numbers.add(num);  // autoboxing happens here
        }
        return numbers;
    }

    // Method to calculate sum (unboxing happens automatically)
    public static int calculateSum(List<Integer> numbers) {
        int sum = 0;
        for (Integer num : numbers) {
            sum += num;  // unboxing Integer -> int
        }
        return sum;
    }

    public static void main(String[] args) {
        // Example input as strings
        String[] inputNumbers = {"10", "20", "30", "40"};

        // Parse strings to Integers
        List<Integer> integerList = parseStringsToIntegers(inputNumbers);

        // Calculate sum
        int result = calculateSum(integerList);

        // Print results
        System.out.println("Input Strings:");
        for (String s : inputNumbers) {
            System.out.print(s + " ");
        }
        System.out.println("\nSum of Integers = " + result);
    }
}
