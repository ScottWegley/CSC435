import java.io.File;
import java.math.BigInteger;
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
        String results[] = new String[lines.size()];

        for (int i = 0; i < lines.size(); i++) {
            int base = Integer.parseInt(lines.get(i).substring(0, lines.get(i).indexOf(" ")));
            BigInteger input = new BigInteger(lines.get(i).substring(lines.get(i).indexOf(" ") + 1));
            Number number = new Number(input, base);
            int counter = 0;
            System.out.println("=====" + input + "  " + base + "=====");
            while (!number.isPalindrome() && !(counter > 500)) {
                counter++;
                input = input.add(number.toBase10Reversed());
                number = new Number(input, base);
            }
            results[i] = (counter > 500 ? ">500\n"
                    : Integer.toString(counter) + " " +
                            number.digits.length + "\n");
            System.out.print(results[i]);
        }
    }

}

class Number {
    int digits[];
    int lastValue;
    private int base;

    Number(BigInteger input, int _base) {
        boolean first = true;
        base = _base;
        while (input != BigInteger.ZERO) {
            BigInteger maxSubtraction = BigInteger.ONE;
            int count = 0;
            while (maxSubtraction.compareTo(input) == -1) {
                maxSubtraction = maxSubtraction.multiply(new BigInteger(String.valueOf(base)));
                count++;
            }
            if (first) {
                first = false;
                digits = new int[count];
                lastValue = count - 1;
            }
            if (maxSubtraction.compareTo(input) == 1) {
                maxSubtraction = maxSubtraction.divide(new BigInteger(String.valueOf(base)));
                count--;
            }
            while (maxSubtraction.compareTo(input) == -1 || maxSubtraction.compareTo(input) == 0) {
                input = input.subtract(maxSubtraction);
                digits[count]++;
            }
        }
    }

    BigInteger toBase10Reversed() {
        BigInteger out = BigInteger.ZERO;
        for (int i = 0; i <= lastValue; i++) {
            out = out.add((new BigInteger(String.valueOf(digits[i]))
                    .multiply(new BigInteger(String.valueOf((int) Math.pow(base, i))))));
        }
        return out;
    }

    boolean isPalindrome() {
        int tail = 0;
        int head = lastValue;
        for (int i = 0; tail <= head; i++) {
            if (digits[tail++] != digits[head--]) {
                return false;
            }
        }
        return true;
    }
}