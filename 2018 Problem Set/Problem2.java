import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Problem2 {

    static String alphabet = "ABCDEFGHIJKLMNOpQRSTUVWXYZ \n";

    public static void main(String[] args) throws Exception {
        String temp = Files.readString(Paths.get("C:\\Code\\CSC435\\2018 Problem Set\\input2.txt")).toLowerCase();
        String input = "";
        HashMap<String, ArrayList<String>> matchSet = new HashMap<>();
        for (int i = 0; i < temp.length(); i++) {
            if (alphabet.toLowerCase().indexOf(temp.charAt(i)) == -1) {
                continue;
            } else {
                input = input + (temp.charAt(i) == '\n' ? " " : temp.charAt(i));
            }
        }
        String[] sortedInput = input.split(" ", 0);
        Arrays.sort(sortedInput);
        for (String s : input.split(" ", 0)) {
            matchSet.put(s, new ArrayList<>());
        }
        int i = 0;
        for (String s : sortedInput) {
            int j = 0;
            if (i == input.split(" ", 0).length) {
                break;
            }
            for (String s2 : sortedInput) {
                if (j <= i) {
                    j++;
                    continue;
                }

                if (isSimilar(s, s2)) {
                    matchSet.get(s).add(s2);
                    matchSet.get(s2).add(s);
                }
            }
            i++;
        }

        for (String s : sortedInput) {
            if (matchSet.get(s).size() == 0) {
                continue;
            }
            System.out.print(s + ":");
            for (String s2 : matchSet.get(s)) {
                System.out.print(" " + s2);
            }
            System.out.print('\n');
        }
    }

    static boolean isSimilar(String against, String check) {
        return Deletion(against, check) || Insertion(against, check) || Replace(against, check)
                || Transpose(against, check);
    }

    static boolean Deletion(String against, String check) {
        boolean out = false;
        for (int i = 0; i < against.length(); i++) {
            char[] temp = against.toCharArray();
            temp[i] = ' ';
            if(String.valueOf(temp).replace(" ", "").equals(check)){
                out = true;
            }
            if(out){
                break;
            }
        }
        return out;
    }

    static boolean Insertion(String against, String check) {
        return Deletion(check, against);
    }

    static boolean Replace(String against, String check) {
        if (against.length() != check.length()) {
            return false;
        }
        char[] cAgainst = against.toCharArray();
        char[] cCheck = check.toCharArray();
        int count = 0;
        for (int i = 0; i < cCheck.length; i++) {
            if (cAgainst[i] != cCheck[i]) {
                count++;
            }
            if (count > 1) {
                break;
            }
        }
        return (count == 1);
    }

    static boolean Transpose(String against, String check) {
        if (against.length() != check.length()) {
            return false;
        }
        char[] cAgainst = against.toCharArray();
        char[] cCheck = check.toCharArray();
        int index = -1;
        int counter = 0;
        for (int i = 0; i < cCheck.length; i++) {
            if (cAgainst[i] != cCheck[i] && index == -1) {
                index = i;
            } else if (cAgainst[i] != cCheck[i]) {
                if ((i - 1) == (index) && cAgainst[i] == cCheck[index] && cCheck[i] == cAgainst[index]) {
                    counter++;
                }
            }
            if (counter > 1) {
                return false;
            }

        }
        return (counter == 1);
    }
}

// ABBA
// BABA