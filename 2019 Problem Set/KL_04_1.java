import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class KL_04_1 {
    public static void main(String[] args) {
        String input = "";
        File file = new File("SC2019-2020_Problem1_input.txt");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                input += scanner.nextLine();
                if (scanner.hasNextLine()) {
                    input += "\n";
                }
            }
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }

        boolean collecting_base = true;
        boolean under_500 = false;

        String base = "";
        String number = "";

        String converted_number = "";
        String reverse_number = "";

        BigInteger decimal_number = new BigInteger("0", 10);
        BigInteger decimal_reverse = new BigInteger("0", 10);
        BigInteger big_sum = new BigInteger("0", 10);

        // Long sum = (long) 0;
        String converted_sum = "";
        String palindrome = "";
        int palindrome_length = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ') {
                collecting_base = false;
            }else if (input.charAt(i) != '\n') {
                if (collecting_base) {
                    base += input.charAt(i);
                }else{
                    number += input.charAt(i);
                }
            }

            if (input.charAt(i) == '\n' || i == input.length() - 1) {
                under_500 = false;
                if (Integer.parseInt(base) != 10) {
                    converted_number = convert_to_base(Integer.parseInt(base), new BigInteger(number, 10));
                    for (int j = converted_number.split("\\s+").length - 1; j >= 0; j--) {
                        reverse_number += converted_number.split("\\s+")[j] + " ";
                    }
                    reverse_number = reverse_number.substring(0, reverse_number.length() - 1);

                    if (converted_number.equals(reverse_number)) { // Is already a palindrome
                        System.out.println(0 + " " + new StringBuilder(reverse_number).reverse().toString().length()) ; // Output
                        under_500 = true;
                    }else{
                        for (int j = 1; j <= 500; j++) {
                            converted_number = convert_to_base(Integer.parseInt(base), new BigInteger(number, 10));
                            reverse_number = "";
                            for (int k = converted_number.split("\\s+").length - 1; k >= 0; k--) {
                                reverse_number += converted_number.split("\\s+")[k] + " ";
                            }
                            reverse_number = reverse_number.substring(0, reverse_number.length() - 1);
                            
                            decimal_number = new BigInteger(number);
                            decimal_reverse = convert_to_decimal(Integer.parseInt(base), reverse_number);
                            big_sum = decimal_number.add(decimal_reverse);
                            
                            converted_sum = convert_to_base(Integer.parseInt(base), big_sum); // .replaceAll("\\s", "")
                            palindrome = "";
                            for(int k = converted_sum.split("\\s+").length - 1; k >= 0; k--) {
                                palindrome += converted_sum.split("\\s+")[k] + " ";
                            }
                            palindrome = palindrome.substring(0, palindrome.length() - 1);
                            palindrome_length = palindrome.replaceAll("[^ ]", "").length() + 1; // Sneaky, very sneaky

                            // System.out.println("Palindrome Check: " + converted_sum.replaceAll("\\s", "") + ", " + palindrome.replaceAll("\\s", "")); // Debug
                            // System.out.println(); // Debug Spacing
                            if (converted_sum.equals(palindrome)) {
                                System.out.println(j + " " + palindrome_length); // Output
                                under_500 = true;
                                break;
                            }else{
                                number = big_sum.toString(10);
                            }
                        }
                    }
                }else{
                    if (number.equals(new StringBuilder(number).reverse().toString())) { // Is already a palindrome
                        System.out.println(0 + " " + number.length()) ; // Output
                        under_500 = true;
                    }else{
                        for (int j = 1; j <= 500; j++) {
                            decimal_number = new BigInteger(number, 10);
                            reverse_number = new StringBuilder(number).reverse().toString();
                            decimal_reverse = new BigInteger(new StringBuilder(number).reverse().toString(), 10);
                            big_sum = decimal_number.add(decimal_reverse);
                            palindrome = new StringBuilder(big_sum.toString(10)).reverse().toString();

                            // System.out.println("Palindrome Check: " + big_sum + ", " + palindrome); // Debug
                            if (big_sum.toString(10).equals(palindrome) || palindrome.length() == 1) {
                                System.out.println(j + " " + palindrome.length());
                                under_500 = true;
                                break;
                            }else{
                                number = big_sum.toString(10);
                            }
                        }
                    }
                }

                if (!under_500) {
                    System.out.println(">500");
                }

                collecting_base = true; 
                base = "";
                number = "";
            }
        }
    }

    public static String convert_to_base(int base, BigInteger number) {
        int max_index = 0;
        BigInteger big_power = BigInteger.valueOf(0);
        for (int i = 0; BigInteger.valueOf(i).compareTo(number) == -1; i++) {
            big_power = BigInteger.valueOf(base).pow(i);
            if (big_power.compareTo(number) == 1) { // big_power > number
                max_index = i - 1;
                break;
            }
        }
        // System.out.print("Base convert from: " + number ); // Debug

        String converted_number = "";
        for (int i = max_index; i >= 0; i--) {
            for (BigInteger j = BigInteger.valueOf(0); j.compareTo(BigInteger.valueOf(base)) <= 0; j = j.add(BigInteger.ONE)) {
                big_power = BigInteger.valueOf(base).pow(i);
                if (big_power.multiply(j.add(BigInteger.ONE)).compareTo(number) == 1) { // big_power * (j + 1) > number
                    number = number.subtract(big_power.multiply(j));
                    converted_number += j + " "; // Add spaces for bases larger than 10
                    break;
                }
            }
        }

        // System.out.println(" to " + converted_number); // Debug
        return converted_number.substring(0, converted_number.length() - 1); // Remove last space
    }

    public static BigInteger convert_to_decimal(int base, String number_string) {
        BigInteger converted_number = BigInteger.valueOf(0);
        int max_index = number_string.replaceAll("[^ ]", "").length(); // Number of spaces
        int exponent = 0;
        String value = "";
        for (int i = 0; i < number_string.length(); i++) {
            if (number_string.charAt(i) != ' ') {
                value += number_string.charAt(i);
            }else{
                converted_number = converted_number.add((BigInteger.valueOf(base).pow(max_index - exponent)).multiply(new BigInteger(value)));
                exponent++;
                value = "";
            }
        }
        converted_number = converted_number.add((BigInteger.valueOf(base).pow(max_index - exponent)).multiply(new BigInteger(value)));

        // System.out.println("Decimal convert from: " + number_string + " to " + converted_number); // Debug
        return converted_number;
    }
}