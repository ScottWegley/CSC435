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
    public static boolean isPalindrome(short base, long input) {
        return false;
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
        return 0;
    }
    }

}
