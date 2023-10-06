import java.io.File;
import java.util.Scanner;

class FirstProblem {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(new File("C:\\Code\\CSC435\\2017 Problem Set\\input1.txt"));
        boolean first = true;
        int size = -1;
        int nVals[] = new int[0];
        int zVals[] = new int[0];
        int xVals[] = new int[0];
        int yVals[] = new int[0];
        int i = 0;
        while (scanner.hasNextLine()) {
            if (first) {
                first = false;
                size = Integer.valueOf(scanner.nextLine().trim());
                nVals = new int[size];
                zVals = new int[size];
                xVals = new int[size];
                yVals = new int[size];
            } else {
                String input = scanner.nextLine();
                nVals[i] = Integer.valueOf(input.trim().split(" ")[0]);
                zVals[i] = Integer.valueOf(input.trim().split(" ")[1]);
                i++;
            }
        }
        for (int j = 0; j < size; j++) {
            int leastError = Integer.MAX_VALUE;
            for (int yTest = zVals[j] - 1; yTest > 1; yTest--) {
                for (int xTest = yTest - 1; xTest > 0; xTest--) {
                    if(getError(xTest, yTest, zVals[j], nVals[j]) < leastError){
                        leastError = getError(xTest, yTest, zVals[j], nVals[j]);
                        xVals[j] = xTest;
                        yVals[j] = yTest;
                    }
                }
            }
        }

        for (int j = 0; j < size; j++) {
            System.out.println(xVals[j] + " " + yVals[j] + " " + getError(xVals[j], yVals[j], zVals[j], nVals[j]));
        }
        
    }

    public static int getError(int x, int y, int z, int n){
        return (int) Math.abs(Math.pow(x,n) + Math.pow(y,n) - Math.pow(z,n));
    }
}