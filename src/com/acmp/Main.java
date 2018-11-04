package com.acmp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Integer> minusIndex = new ArrayList<>();
    private static ArrayList<Integer> plusIndex = new ArrayList<>();
    private static ArrayList<Integer> zeroIndex = new ArrayList<>();
    private static ArrayList<Integer> numbers = new ArrayList<>();
    private static int numbersLength;
    private static int minusSum = 0;
    private static int plusSum = 0;

    public static void main(String[] args) throws FileNotFoundException, CustomException {

        readFile();
        splitNums();
        writeFile();

    }

    private static void readFile() throws FileNotFoundException, CustomException {
        FileInputStream file = new FileInputStream("INPUT.txt");
        Scanner inputFile = new Scanner(file);
        numbersLength = Integer.parseInt(inputFile.nextLine());
        String line = inputFile.nextLine();
        final String[] array = line.split(" ");
        for(int i = 0; i < array.length; i++){
            numbers.add(Integer.parseInt(array[i]));
        }
        if (numbersLength < 1 || numbersLength > 10000) {
            throw new CustomException("Please, check INPUT.txt file. 1 <= N <= 10000");
        }
    }

    private static void splitNums(){
        for(int i = 0; i < numbers.size(); i++){
            if(numbers.get(i)>0){
                plusSum += numbers.get(i);
                plusIndex.add(i + 1);
            } else if (numbers.get(i)<0){
                minusSum -= numbers.get(i);
                minusIndex.add(i + 1);
            } else {
                zeroIndex.add(i + 1);
            }
        }
    }

    private static void writeFile() {
        File file = new File("OUTPUT.txt");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
            if (minusSum > plusSum) {
                writer.println(minusIndex.size());
                writer.print(minusIndex.get(0));
                for (int i = 1; i < minusIndex.size(); i++) {
                    writer.print(" " + minusIndex.get(i));
                }
            } else if (minusSum == 0 && plusSum == 0) {
                writer.println(zeroIndex.size());
                writer.print(zeroIndex.get(0));
                for (int i = 1; i < zeroIndex.size(); i++) {
                    writer.print(" " + zeroIndex.get(i));
                }
            } else {
                writer.println(plusIndex.size());
                writer.print(plusIndex.get(0));
                for (int i = 1; i < plusIndex.size(); i++) {
                    writer.print(" " + plusIndex.get(i));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            writer.flush();
            writer.close();
        }
    }

}

class CustomException extends Exception {

    public CustomException(String message) {
        super(message);
    }

}