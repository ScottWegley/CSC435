import java.io.File;
import java.util.Scanner;

public class Problem5 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("C:\\Code\\CSC435\\2018 Problem Set\\input5.txt"));
        int n = -1;
        Pair[] pairs = null;
        int pairIndex = 0;
        while (scanner.hasNextLine()) {
            if (n == -1) {
                n = Integer.valueOf(scanner.nextLine());
                pairs = new Pair[n];
            } else {
                String[] in = scanner.nextLine().split(" ");
                pairs[pairIndex] = new Pair(in[0], in[1]);
                pairIndex++;
            }
        }
        boolean[] match = new boolean[n];
        for (int i = 0; i < match.length; i++) {
            for (int j = 2; j <= 36; j++) {
                for (int j2 = 2; j2 <= 36; j2++) {
                    try {
                        int a = Integer.valueOf(pairs[i].a, j);
                        int b = Integer.valueOf(pairs[i].b, j2);
                        if (a == b) {
                            match[i] = true;
                        }
                    } catch (Exception e) {
                    } finally {

                    }
                }
            }
        }

        for (boolean b : match) {
            System.out.println((b ? "yes" : "no"));
        }
    }
}

class Pair {
    String a;
    String b;

    Pair(String _a, String _b) {
        a = _a;
        b = _b;
    }

    @Override
    public String toString() {
        return a + "   " + b;
    }
}