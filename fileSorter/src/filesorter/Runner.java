/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesorter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
 
/**
 *
 * @author Corjan
 */
public class Runner {
    public static void main(String[] args) {
        System.out.println("This program will be used to filter data from a file. You can see the full data set below");
        FileLoader input = new FileLoader("dataFile.txt");
        Scanner keyboard = new Scanner(System.in);
        input.printAlldata();
        System.out.println("Please state which columns of data you wish see seperated with a \",\" For example 1,3");
        for(int i=1;i<input.getNumberOfColumns();i++)
            System.out.println(i+"\t"+input.getVeriableData(0, i));
        String desiredColumns = keyboard.nextLine();
        System.out.println("Please state which ID of data you wish see seperated with a \",\" For example 1,3,5");
        for(int i=1;i<input.getNumberOfRows();i++)
            System.out.println(i+"\t"+input.getVeriableData(i, 0));
        String desiredRows = keyboard.nextLine();
        FileFilter output = new FileFilter(input.getData(), desiredRows, desiredColumns);
        output.printFilteredData();
    }
    
}
