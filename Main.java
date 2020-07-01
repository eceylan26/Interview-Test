package com.example.ersin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        int[] firstArr={4,12,9,5,6,1};
        int[] secondArr={4,9,12,6};

        System.out.println(findMissing(firstArr,secondArr));

        System.out.println("--------------------------");

        System.out.println(Arrays.toString(rotate(firstArr, 12)));

        System.out.println("--------------------------");

        readFromFile("C:\\Users\\Ersin\\Desktop\\sample.txt");

    }

    public static List<Integer> findMissing(int[] firstArr, int [] secondArr ){

        List<Integer> result = new ArrayList<>();

        List<Integer> firstList = Arrays.stream(firstArr).boxed().collect(Collectors.toList());
        List<Integer> secondList = Arrays.stream(secondArr).boxed().collect(Collectors.toList());

        firstList.stream().forEach(elementOfFirst -> {
            if(!secondList.contains(elementOfFirst)){
                result.add(elementOfFirst);
            }
        });

        return result;
    }

    public static int[] rotate(int[] arr, int rotateNumber){

        int position = -1;

        for(int i=0;i<arr.length;i++){
            if(rotateNumber==arr[i]){
                position=i;
                break;
            }
        }

        if(position==-1){
            System.out.println("Not found rotate number in array");
            return arr;
        }
        else {
            processRotate(arr,position+1);
        }

        return arr;

    }

    public static void processRotate(int[] arr, int timeofRepeat){

        int count=0;

        while(count!=timeofRepeat){
            int temp = arr[0];

            System.arraycopy(arr, 1, arr, 0, arr.length - 1);

            arr[arr.length-1]=temp;
            count++;
        }

    }

    public static void readFromFile(String path){
        StringBuilder result = new StringBuilder();
        BufferedReader reader;
        int countCaseNumber=0;
        int countTestNumber=0;
        ArrayList<String> accountNumber = new ArrayList();

        try {
            reader = new BufferedReader(new FileReader(path));
            int caseNumber=Integer.parseInt(reader.readLine());

            while (countCaseNumber!=caseNumber){
                countTestNumber=0;
                int testNumber = Integer.parseInt(reader.readLine());

                HashMap<String,Integer> numberofInfo = new HashMap<>();

                while (countTestNumber!=testNumber){
                    String line = reader.readLine();
                    if(accountNumber.contains(line)){
                        int index= accountNumber.indexOf(line);
                        int numberOfElement=numberofInfo.get(line)+1;
                        numberofInfo.remove(index+1);
                        numberofInfo.put(line,numberOfElement);

                    }else{
                        accountNumber.add(line);
                        numberofInfo.put(line,1);
                    }

                    countTestNumber++;
                }
                reader.readLine();
                countCaseNumber++;
                Collections.sort(accountNumber);

                for(int i=0;i<accountNumber.size();i++){
                    result.append(accountNumber.get(i) + " " + numberofInfo.get(accountNumber.get(i)) + "\n");
                }

                result.append("\n");
                accountNumber.clear();
                numberofInfo.clear();
            }
            reader.close();

            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
