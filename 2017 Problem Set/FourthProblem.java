import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class FourthProblem {

    static ArrayList<ArrayList<Integer>> paths = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("C:\\Code\\CSC435\\2017 Problem Set\\input4.txt"));
        String input = "";
        while (scanner.hasNextLine()) {
            input = input + '\n';
            String s = scanner.nextLine();
            input = input + s;
        }
        for (String testCase : input.split("0")) {
            testCase = testCase.replaceFirst("\n", "");
            String inputArr[] = testCase.split("\n");
            int maxPower = Integer.valueOf(inputArr[0]);
            int tradeStart = Integer.valueOf(inputArr[1].split(" ")[0]);
            int tradeEnd = Integer.valueOf(inputArr[1].split(" ")[1]);
            // System.out.println("TEST CASE: POW: " + maxPower + " || START:" + tradeStart
            // + " || END: " + tradeEnd);
            ArrayList<Map.Entry<Integer, Integer>>[] cardMap = new ArrayList[maxPower + 1];
            for (int i = 0; i < cardMap.length; i++) {
                cardMap[i] = new ArrayList<>();
            }
            for (int i = 2; i < inputArr.length; i++) {
                int tempStart = Integer.valueOf(inputArr[i].split(" ")[0]);
                int tempEnd = Integer.valueOf(inputArr[i].split(" ")[1]);
                int cost = Integer.valueOf(inputArr[i].split(" ")[2]);
                cardMap[tempStart].add(Map.entry(tempEnd, cost));
                cardMap[tempEnd].add(Map.entry(tempStart, cost));
            }
            // BEFORE THIS IS SAFE
            paths = new ArrayList<>();
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(tradeStart);
            recurse(cardMap, tradeStart, temp, tradeEnd);
            int minCost = Integer.MAX_VALUE;
            for (ArrayList<Integer> list : paths) {
                if (calculateCost(list, cardMap) < minCost) {
                    minCost = calculateCost(list, cardMap);
                }
            }
            System.out.println(minCost);

        }
    }

    static void recurse(ArrayList<Map.Entry<Integer, Integer>>[] map, int cur, ArrayList<Integer> path, int endNode) {
        if (cur == endNode) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            for (Integer i : path) {
                temp.add(i);
            }
            paths.add(temp);
            return;
        }
        for (Map.Entry<Integer, Integer> entry : map[cur]) {
            if (path.contains(entry.getKey())) {
                continue;
            }
            path.add(entry.getKey());
            recurse(map, entry.getKey(), path, endNode);
            path.remove(entry.getKey());
        }
    }

    static int calculateCost(ArrayList<Integer> path, ArrayList<Map.Entry<Integer, Integer>>[] map) {
        int cost = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            for (Map.Entry<Integer,Integer> entry : map[path.get(i)]) {
                if(entry.getKey() == path.get(i+1)){
                    cost += entry.getValue();
                    break;
                }
            }
        }
        return cost;
    }
}
