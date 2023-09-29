import java.io.File;
import java.util.Scanner;

public class Problem4 {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("C:\\Code\\CSC435\\2018 Problem Set\\input4.txt"));
        int n = -1;
        boolean x = true;
        double[] xVals = new double[4];
        double[] yVals = new double[4];
        int xIndex = 0;
        int yIndex = 0;
        while (scanner.hasNextDouble()) {
            if (n == -1) {
                n = scanner.nextInt();
            } else {
                if (x) {
                    xVals[xIndex++] = scanner.nextDouble();
                } else {
                    yVals[yIndex++] = scanner.nextDouble();
                }
                x = !x;
            }
        }
        double xOffset = Integer.MAX_VALUE;
        double yOffset = Integer.MAX_VALUE;

        double[] xTemp = new double[4];
        double[] yTemp = new double[4];

        for (int i = 0; i < yVals.length; i++) {
            xTemp[i] = xVals[i];
            yTemp[i] = yVals[i];
        }

        for (int i = 0; i < yTemp.length; i++) {
            if (xTemp[i] < 0) {
                if (yTemp[i] < 0) {
                    xVals[0] = xTemp[i];
                    yVals[0] = yTemp[i];
                } else {
                    xVals[3] = xTemp[i];
                    yVals[3] = yTemp[i];
                }
            } else {
                if (yTemp[i] < 0) {
                    yVals[1] = yTemp[i];
                    xVals[1] = xTemp[i];
                } else {
                    yVals[2] = yTemp[i];
                    xVals[2] = xTemp[i];
                }
            }
        }

        for (int i = 0; i < yVals.length; i++) {
            if (xVals[i] < xOffset) {
                xOffset = xVals[i];
            }
            if (yVals[i] < yOffset) {
                yOffset = yVals[i];
            }
        }

        for (int i = 0; i < yVals.length; i++) {
            xVals[i] += Math.abs(xOffset);
            yVals[i] += Math.abs(yOffset);
            xVals[i] = Math.round(xVals[i] * Math.pow(10, 6)) / Math.pow(10, 6);
            yVals[i] = Math.round(yVals[i] * Math.pow(10, 6)) / Math.pow(10, 6);
        }

        double totalArea = (0.5) * (xVals[0] * yVals[1] - yVals[0] * xVals[1] + xVals[1] * yVals[2]
                - yVals[1] * xVals[2] + xVals[2] * yVals[3] - yVals[2] * xVals[3] + xVals[3] * yVals[0]
                - yVals[3] * xVals[0]);

        int k = 1;
        int counter = 0;
        while (k != n) {
            k *= 2;
            counter++;
        }
        int[] square = new int[counter + 1];
        int squareIndex = 0;
        System.out.println("Size of " + counter);
        while (n / 2 >= 1) {
            while(n * n < totalArea){
                totalArea -= n*n;
                square[squareIndex]++;
            }
            squareIndex++;
            n /= 2;
        }

        for (int i = 0; i < square.length - 1; i++) {
            System.out.print(square[i] + " ");
        }
        System.out.println(square[square.length - 1]);
    }
}
