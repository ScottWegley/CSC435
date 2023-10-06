import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class SecondProblem {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("C:\\Code\\CSC435\\2017 Problem Set\\input2.txt"));
        int size = Integer.valueOf(scanner.nextLine());
        ArrayList<String> operations[] = new ArrayList[size];
        int nVals[] = new int[size];
        for (int i = 0; i < size; i++) {
            operations[i] = new ArrayList<>();
            String input = scanner.nextLine().trim();
            nVals[i] = Integer.valueOf(input.split(" ")[0]);
            int listSize = Integer.valueOf(input.split(" ")[1]);
            for (int j = 0; j < listSize; j++) {
                operations[i].add(scanner.nextLine());
            }
        }

        for (int i = 0; i < size; i++) {
            boolean board[][] = new boolean[nVals[i]][nVals[i]];
            for (String s : operations[i]) {
                int xLow = Integer.valueOf(s.split(" ")[0]);
                int xHigh = Integer.valueOf(s.split(" ")[1]);
                int yLow = Integer.valueOf(s.split(" ")[2]);
                int yHigh = Integer.valueOf(s.split(" ")[3]);
                for (int xCoord = xLow - 1; xCoord < xHigh; xCoord++) {
                    for (int yCoord = yLow - 1; yCoord < yHigh; yCoord++) {
                        board[yCoord][xCoord] = !board[yCoord][xCoord];
                    }
                }
            }
            int blackCount = 0;
            for (int j = 0; j < nVals[i]; j++) {
                for (int j2 = 0; j2 < nVals[i]; j2++) {
                    blackCount += (board[j][j2] ? 1 : 0);
                }
            }
            System.out.println(blackCount);
        }

    }
}
