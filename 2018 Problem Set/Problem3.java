import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Problem3 {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("C:\\Code\\CSC435\\2018 Problem Set\\input3.txt"));

        ArrayList<Point> points = new ArrayList<>();
        int n = -1;
        while (scanner.hasNextInt()) {
            if (n == -1) {
                n = scanner.nextInt();
            } else {
                points.add(new Point(scanner.nextInt(), scanner.nextInt()));
            }
        }

        ArrayList<Point> groupOne = new ArrayList<>();
        ArrayList<Point> groupTwo = new ArrayList<>();

        Point originOne = points.get(0);
        groupOne.add(points.get(0));
        points.remove(points.get(0));

        Point originTwo = new Point(originOne.x, originOne.y);
        for (Point point : points) {
            if(Point.Distance(originOne, point) > Point.Distance(originOne, originTwo)){
                originTwo = point;
            }
        }

        groupTwo.add(originTwo);
        points.remove(originTwo);

        while(points.size() > 0){
            Point shortestPossible = points.get(0);
            for (int i = 1; i < points.size(); i++) {
                if(Math.min(Point.Distance(originOne, points.get(i)),Point.Distance(originTwo, points.get(i))) < Math.min(Point.Distance(originTwo, shortestPossible),Point.Distance(originOne, shortestPossible))){
                    shortestPossible = points.get(i);
                }
            }
            points.remove(shortestPossible);
            if(Point.Distance(originOne, shortestPossible) < Point.Distance(originTwo, shortestPossible)){
                groupOne.add(shortestPossible);
            } else {
                groupTwo.add(shortestPossible);
            }
        }

        int i=0;
        int distanceOne = -1;
        for (Point point : groupOne) {
            int j = 0;
            for (Point point2 : groupOne) {
                if(j <= i){
                    j++;
                    continue;
                } else {
                    if(Point.Distance(point, point2) > distanceOne){
                        distanceOne = Point.Distance(point, point2);
                    }
                }
                j++;
            }
            i++;
        }
        i=0;
        int distanceTwo = -1;
        for (Point point : groupTwo) {
            int j = 0;
            for (Point point2 : groupTwo) {
                if(j <= i){
                    j++;
                    continue;
                } else {
                    if(Point.Distance(point, point2) > distanceTwo){
                        distanceTwo = Point.Distance(point, point2);
                    }
                }
                j++;
            }
            i++;
        }
        System.out.println(Math.max(distanceOne,distanceTwo));
        groupOne.forEach((p) -> System.out.println(p));
        System.out.println("===");
        groupTwo.forEach((p) -> System.out.println(p));
    }

}

class Point {
    int x;
    int y;

    Point(int _x, int _y) {
        x = _x;
        y = _y;
    }

    static int Distance(Point p1, Point p2){
        return Math.abs(p1.x - p2.x) + Math.abs(p2.y-p1.y);
    }

    @Override
    public String toString() {
        return this.x + " " + this.y;
    }
}