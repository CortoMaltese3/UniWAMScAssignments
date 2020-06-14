package exerciseapplication;

import java.util.ArrayList;

public class Controller {
    
    int arraySize = Helper.UserIntegerInput();         
    int[] integerArray = Helper.CreateArray(arraySize);
    
    public void IntegerArraySum(){
        System.out.println("\nQuestion i");
        int integerSum = 0;
        for (int i = 0; i < integerArray.length; i++) {
            integerSum += integerArray[i];
        }
        System.out.println("The sum of the " + integerArray.length + " integers of the array is: " + integerSum);            
    }
    
    public void IntegerArrayAverage(){
        System.out.println("\nQuestion ii");
        double integerAverage = 0;
        int integerSum = 0;
        for (int i = 0; i < integerArray.length; i++) {
            integerSum += integerArray[i];
        }
        try {
            integerAverage = integerSum / integerArray.length;
        } catch (Exception e) {
            System.out.println(e.getMessage());      
        }
        System.out.println("The average of the " + integerArray.length + " integers of the array is: " + integerAverage);            
    }
    
    public void IntegerArrayMax(){
        System.out.println("\nQuestion iii");
        int maxInteger = integerArray[0];
        for (int i = 0; i < integerArray.length; i++) {
            if (integerArray[i] > maxInteger) {
                maxInteger = integerArray[i];
            }
        }
        System.out.println("The max integer of the " + integerArray.length + " integers of the array is: " + maxInteger);  
    }
    
    public void IntegerArrayOddNumbers(){
        System.out.println("\nQuestion iv");
        ArrayList<Integer> oddIntegerList = new ArrayList<>();
        for (int i = 0; i < integerArray.length; i++) {
            if (integerArray[i] / 2 == 1) {
                oddIntegerList.add(integerArray[i]);                
            }
        }
        System.out.println("The number of odd integers in the array is: " + oddIntegerList.size());  
    }
    
    public void IntegerArraySearchForNumber(){  
        System.out.println("\nQuestion v\nSearch an integer in the array");
        int userIntegerInput = Helper.UserIntegerInput(); 
        ArrayList<Integer> integersMatchingUserInput = new ArrayList<>();
        for (int i = 0; i < integerArray.length; i++) {
            if (integerArray[i] == userIntegerInput) {
                integersMatchingUserInput.add(integerArray[i]);
                System.out.println("Integer " + userIntegerInput + " was found in the array at position " + i);  
            }
        }
        if (integersMatchingUserInput.isEmpty()) {
            System.out.println("Integer " + userIntegerInput + " was not found in the array");  
        }
    }    
}
