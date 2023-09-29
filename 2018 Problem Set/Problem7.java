import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Problem7 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("C:\\Code\\CSC435\\2018 Problem Set\\input7.txt"));
        String input = "";
        while (scanner.hasNextLine()) {
            input = input + scanner.nextLine().replaceAll("\n", "");
        }
        String[] lineText = input.split(";");
        ArrayList<Line> lines = new ArrayList<>();
        for (int i = 0; i < lineText.length; i++) {
            String ptA = lineText[i].substring(1, lineText[i].indexOf(")"));
            MyPoint a = new MyPoint(Integer.valueOf(ptA.split(",")[0]),
                    Integer.valueOf(ptA.split(",")[1]));
            String ptB = lineText[i].substring(lineText[i].indexOf(")") + 3);
            ptB = ptB.substring(0, ptB.length() - 1);
            MyPoint b = new MyPoint(Integer.valueOf(ptB.split(",")[0]),
                    Integer.valueOf(ptB.split(",")[1]));
            lines.add(new Line(a, b));
        }

        ArrayList<ArrayList<Line>> shapes = new ArrayList<>();
        while (lines.size() != 0) {
            ArrayList<Line> sLine = new ArrayList<>();
            Line toAdd = lines.get(0);
            sLine.add(toAdd);

            ArrayList<Line> toCheck = new ArrayList<>();
            toCheck.add(toAdd);
            while (toCheck.size() != 0) {
                boolean hasMatch = false;
                for (Line l : lines) {
                    if (toCheck.get(0).touching(l) && !sLine.contains(l)) {
                        toCheck.add(l);
                        sLine.add(l);
                        hasMatch = true;
                    }
                }
                if (!hasMatch) {
                    toCheck.remove(0);
                }
            }
            shapes.add(sLine);
            sLine.forEach((q) -> lines.remove(q));
        }

        int polygons = 0;
        int figure = 0;
        for (ArrayList<Line> shape : shapes) {
            HashMap<String, Integer> pointMap = new HashMap<>();
            for (Line l : shape) {
                if (!pointMap.keySet().contains(l.a.toString())) {
                    pointMap.put(l.a.toString(), 1);
                } else {
                    pointMap.put(l.a.toString(), pointMap.get(l.a.toString()) + 1);
                }
                if (!pointMap.keySet().contains(l.b.toString())) {
                    pointMap.put(l.b.toString(), 1);
                } else {
                    pointMap.put(l.b.toString(), pointMap.get(l.b.toString()) + 1);
                }
                
            }
            boolean degreeOfTwo = true;
            for (String p : pointMap.keySet()) {
                if(pointMap.get(p) != 2){
                    degreeOfTwo = false;
                }
            }
            if (degreeOfTwo) {
                polygons++;
            }
            figure++;
        }

        System.out.print(figure + " " + polygons);
    }
}

class MyPoint {
    int x;
    int y;

    MyPoint(int _x, int _y) {
        x = _x;
        y = _y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Test
     * 
     * @param in
     * @param obj
     * @return
     */
    public static boolean isEqual(MyPoint in, MyPoint obj) {
        return (obj).getX() == in.getX() && (obj).getY() == in.getY();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof MyPoint){
            return ((MyPoint)obj).getX() == this.x && ((MyPoint)obj).getY() == this.y;
        }
        return false;
    }
}

class Line {
    MyPoint a;
    MyPoint b;

    Line(MyPoint _a, MyPoint _b) {
        a = _a;
        b = _b;
    }

    @Override
    public String toString() {
        return "From " + a + " To " + b;
    }

    boolean touching(Line l) {
        return MyPoint.isEqual(l.a, this.a) || MyPoint.isEqual(l.a, this.b) || MyPoint.isEqual(this.a, l.b)
                || MyPoint.isEqual(l.b, this.b);
    }
}