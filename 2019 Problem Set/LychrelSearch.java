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

    }

}
