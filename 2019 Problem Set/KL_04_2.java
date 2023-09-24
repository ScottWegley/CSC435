import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class KL_04_2 {
    public static void main(String[] args) {
        String input = "";
        File file = new File("SC2019-2020_Problem8_input.txt");
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

        int count = 0;
        String[] input_array = input.split("\\r?\\n|\\r");
        for (int i = 1; i < input_array.length; i++) {
            if (Integer.parseInt(input_array[i - 1]) > Integer.parseInt(input_array[i])) {
                count++;
            }
        }

        System.out.println(count);
    }
}