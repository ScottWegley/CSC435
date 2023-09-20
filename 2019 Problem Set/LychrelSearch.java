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
        String results[] = new String[lines.size()];

        for (int i = 0; i < lines.size(); i++) {
            int base = Integer.parseInt(lines.get(i).substring(0, lines.get(i).indexOf(" ")));
            long input = Long.parseLong(lines.get(i).substring(lines.get(i).indexOf(" ") + 1));
            Number number;
            int counter = 0;
            do {
                counter++;
                number = new Number(input, base);
                input = input + number.toBase10();
            } while (!number.isPalindrome() && counter <= 500);
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

    Number(long input, int base) {
        boolean first = true;
        while (input != 0) {
            long maxSubtraction = 1;
            int count = 0;
            while (maxSubtraction < input && maxSubtraction * base > 0) {
                maxSubtraction *= base;
                count++;
            }
            if (first) {
                first = false;
                digits = new int[count + 1];
                lastValue = count;
            }
            if (maxSubtraction > input) {
                maxSubtraction /= base;
                count--;
            }
            while (maxSubtraction <= input) {
                input -= maxSubtraction;
                digits[count]++;
            }
        }
    }

    long toBase10() {
        long out = 0;
        for (int i = 0; i < digits.length; i++) {
            out += digits[i] * Math.pow(base, i);
        }
        return out;
    }

    boolean isPalindrome() {
        int tail = 0;
        int head = lastValue;
        for (int i = 0; i < lastValue / 2 && tail <= head; i++) {
            if (digits[tail++] != digits[head--]) {
                return false;
            }
        }
        return true;
    }
}