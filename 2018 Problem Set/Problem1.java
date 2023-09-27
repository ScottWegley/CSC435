import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeMap;

class Problem1 {

    static TreeMap<String, Integer> fileList = new TreeMap<>();
    static TreeMap<String, FileData> backupList = new TreeMap<>();

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("C:\\Code\\CSC435\\2018 Problem Set\\input.txt"));
            boolean secondBlock = false;
            while (scanner.hasNextLine()) {
                String in = scanner.nextLine();
                if (in == "") {
                    secondBlock = true;
                    continue;
                }
                if (!secondBlock) {
                    fileList.put(in, 0);
                } else {
                    String partialName = in.substring(0, in.indexOf("_"));
                    if (backupList.keySet().contains(partialName)) {
                        backupList.get(partialName)._s.add(in);
                    } else {
                        backupList.put(partialName, new FileData(in, 0));
                    }
                }
            }
            scanner.close();
            //Counting
            for (String fileName : fileList.keySet()) {
                if (backupList.keySet().contains(fileName)) {
                    fileList.put(fileName, 1);
                }
            }

            for (String fileName : backupList.keySet()) {
                if(fileList.keySet().contains(fileName)){
                    backupList.get(fileName)._i = 1;
                }
            }

            boolean printed = false;
            //Printing
            for(String fileName : backupList.keySet()){
                if(backupList.get(fileName)._i == 0){
                    Collections.sort(backupList.get(fileName)._s);
                    for (String temp : backupList.get(fileName)._s) {
                        System.out.println("F " + temp);
                        printed = true;
                    }
                }
            }

            for (String fileName : fileList.keySet()) {
                if(fileList.get(fileName) == 0){
                    System.out.println("I " + fileName);
                    printed = true;
                }
            }
            if(!printed){
                System.out.println("No mistmatches");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class FileData {
    ArrayList<String> _s = new ArrayList<>();
    int _i;

    FileData(String s, int i) {
        _s.add(s);
        _i = i;
    }
}