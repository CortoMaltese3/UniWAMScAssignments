/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exerciseapplication;
import java.util.Random;
import java.util.Scanner;

public class Helper {
    
    static int[] CreateArray(int arraySize){
    int[] integerArray = null;
    Random random = new Random();
    if (IsValidArraySize(arraySize)) {
        integerArray = new int[arraySize];            
    }     
        for (int i = 0; i < integerArray.length; i++) {
            integerArray[i] = random.nextInt(10);
        }
    
    return integerArray;
    }
        
    static int UserIntegerInput(){
        int arraySize = 0;
        Scanner scanner = new Scanner(System.in);
        
        do {
            System.out.print("Enter a valid integer: ");
            while (!scanner.hasNextInt()) {  
                String input = scanner.next();
                System.out.print("Enter a valid integer: ");
            }          
            arraySize = scanner.nextInt();
        } while (arraySize < 0);
        return arraySize;
    }
    
    private static boolean IsValidArraySize(int arraySize){
        return !(arraySize < 0 || arraySize > Integer.MAX_VALUE);
    }
}
