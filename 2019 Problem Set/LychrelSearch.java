import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class LychrelSearch {
    public static void main(String[] args) {

        File file = new File("C:\\Code\\CSC435\\2019 Problem Set\\LychrelNumbers.txt");
        Scanner scan = null;
        try {
            scan = new Scanner(file);

        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<String> lines = new ArrayList<String>();
        while (scan.hasNextLine()) {
            lines.add(scan.nextLine());
        }
        for (int i = 0; i < lines.size(); i++) {
            System.out.print("Trying " + lines.get(i).substring(lines.get(i).indexOf(" ") + 1) + " in base "
                    + lines.get(i).substring(0,
                            lines.get(i).indexOf(" "))
                    + "  ||  " + lychrelSearch(Short.valueOf(lines.get(i).substring(0,
                            lines.get(i).indexOf(" "))),
                            Long.valueOf(lines.get(i).substring(lines.get(i).indexOf(" ") + 1))));
        }
    }

    public static String lychrelSearch(short base, long input) {
        int counter = 0;
        while (counter <= 500 && !isPalindrome(base, input)) {
            System.out.println((counter + 1) + ": " + input + " + " + reverse(input) + " = "
                    + (input + reverse(input)) + " || " +
                    digitsToString(getBaseDigits((input + reverse(input)), base)));
            input += reverse(input);
            counter++;
        }
        return (counter > 500 ? ">500\n" : (counter + " " + length(input, base) + "\n"));
    }

    public static boolean isPalindrome(short base, long input) {
        short[] digits = getBaseDigits(input, base);
        int tail = 0;
        int head = digits.length - 1;
        while (tail <= head) {
            if (digits[tail++] != digits[head--]) {
                return false;
            }
        }
        return true;
    }

    public static long reverse(long input) {
        long out = 0;
        while (input != 0) {
            out *= 10;
            out += input % 10;
            input /= 10;
        }
        return out;
    }

    public static int length(long input, short base) {
        return getBaseDigits(input, base).length;
    }

    private static short[] getBaseDigits(long input, Short base) {
        int count = 1;
        short[] digits = new short[1];
        long maxSub = 1;
        int lastValue = -1;
        boolean first = true;
        while (input != 0) {
            while (maxSub < input && maxSub * base > 0) {
                maxSub *= base;
                count++;
            }
            if (maxSub > input) {
                maxSub /= base;
                count--;
            }
            if (first) {
                first = false;
                digits = new short[count];
                lastValue = count - 1;
            }
            while (maxSub <= input) {
                input -= maxSub;
                digits[lastValue - (count - 1)]++;
            }
        }
        return digits;
    }

    public static String digitsToString(short[] _d) {
        String out = "";
        for (short s : _d) {
            out = out + s + " ";
        }
        return out;
    }

}
