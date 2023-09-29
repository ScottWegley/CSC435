import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Problem3 {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("C:\\Code\\CSC435\\2018 Problem Set\\input3.txt"));

        ArrayList<MyPoint> points = new ArrayList<>();
        int n = -1;
        while (scanner.hasNextInt()) {
            if (n == -1) {
                n = scanner.nextInt();
            } else {
                points.add(new MyPoint(scanner.nextInt(), scanner.nextInt()));
            }
        }

        ArrayList<MyPoint> groupOne = new ArrayList<>();
        ArrayList<MyPoint> groupTwo = new ArrayList<>();

        MyPoint originOne = points.get(0);
        groupOne.add(points.get(0));
        points.remove(points.get(0));

        MyPoint originTwo = new MyPoint(originOne.x, originOne.y);
        for (MyPoint point : points) {
            if (MyPoint.Distance(originOne, point) > MyPoint.Distance(originOne, originTwo)) {
                originTwo = point;
            }
        }

        groupTwo.add(originTwo);
        points.remove(originTwo);

        while (points.size() > 0) {
            MyPoint shortestPossible = points.get(0);
            for (int i = 1; i < points.size(); i++) {
                if (Math.min(findShortestConnection(groupOne, points.get(i)),
                        findShortestConnection(groupTwo, points.get(i))) < Math
                                .min(findShortestConnection(groupOne, shortestPossible),
                                        findShortestConnection(groupTwo, shortestPossible))) {
                    shortestPossible = points.get(i);
                }
            }
            points.remove(shortestPossible);
            if (findShortestConnection(groupOne, shortestPossible) < findShortestConnection(groupTwo,
                    shortestPossible)) {
                groupOne.add(shortestPossible);
            } else {
                groupTwo.add(shortestPossible);
            }
        }

        int i = 0;
        int distanceOne = -1;
        for (MyPoint point : groupOne) {
            int j = 0;
            for (MyPoint point2 : groupOne) {
                if (j <= i) {
                    j++;
                    continue;
                } else {
                    if (MyPoint.Distance(point, point2) > distanceOne) {
                        distanceOne = MyPoint.Distance(point, point2);
                    }
                }
                j++;
            }
            i++;
        }
        i = 0;
        int distanceTwo = -1;
        for (MyPoint point : groupTwo) {
            int j = 0;
            for (MyPoint point2 : groupTwo) {
                if (j <= i) {
                    j++;
                    continue;
                } else {
                    if (MyPoint.Distance(point, point2) > distanceTwo) {
                        distanceTwo = MyPoint.Distance(point, point2);
                    }
                }
                j++;
            }
            i++;
        }
        System.out.println(Math.max(distanceOne, distanceTwo));
        groupOne.forEach((p) -> System.out.println(p));
        System.out.println("===");
        groupTwo.forEach((p) -> System.out.println(p));
    }

    public static int findShortestConnection(ArrayList<MyPoint> group, MyPoint check) {
        int shortest = Integer.MAX_VALUE;
        for (MyPoint point : group) {
            if (MyPoint.Distance(point, check) < shortest) {
                shortest = MyPoint.Distance(point, check);
            }
        }
        return shortest;
    }

}

class Point {
    int x;
    int y;

    Point(int _x, int _y) {
        x = _x;
        y = _y;
    }

    static int Distance(MyPoint p1, MyPoint p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p2.y - p1.y);
    }

    @Override
    public String toString() {
        return this.x + " " + this.y;
    }
}