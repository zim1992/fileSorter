package filesorter;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Corjan
 */
public class FileLoader {
     private Scanner doc;
     private ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
     /**
     * Constructor which takes the default file dataFile.txt
     */
    public FileLoader(){
         try{
            File loaded = new File("dataFile.txt");
            doc = new Scanner(loaded);
            String newLine = doc.nextLine();
            char[] car = newLine.toCharArray();
        }catch(FileNotFoundException e){
            System.err.println("File could not be found ");            
        }
    }
    /**
     * Takes file with Type File containing data from a flatfile
     * @param file Name of the data file you wish to load
     */
    public FileLoader(String file){
         try{
            File loaded = new File(file);
            doc = new Scanner(loaded);
            fileToArray();
        }catch(FileNotFoundException e){
            System.err.println("File could not be found ");            
        }
    }
   
    /**
     * This method is used to convert the data from the file into a list array.
     */
    private void fileToArray(){
        boolean firstRow= true;
        while(doc.hasNext()){
            int tabCharOld=0;
            int tabCharNew=0;
            addRow();
            String newLine = doc.nextLine();
            char[] charString = newLine.toCharArray();
            for(int i=0;i<charString.length;i++){                    
                if(charString[i]==9){
                    boolean firstNumber =tabCharOld==0;
                    if(firstNumber){
                        tabCharOld= tabCharNew;
                        tabCharNew = i-1;                      
                    }else{
                        tabCharOld= tabCharNew+2;
                        tabCharNew = i-1;                        
                    }
                    addValueToRow(newLine.substring(tabCharOld,tabCharNew+1));
                    if(firstNumber)
                        tabCharOld=tabCharNew+2;                    
                }             
            }
            addValueToRow(newLine.substring(tabCharNew+2));
        }
    }
    /**
     * This method adds a new row to the array list
     */
    private void addRow(){
        data.add(new ArrayList<String>());        
    }
    /**
     * Adds a value to a row
     * @param value this is a string containing information wished to be added
     */
    private void addValueToRow(String value){
	data.get(data.size()-1).add(value);
    }
    /**
     * @return Number of rows as an int
     */
    public int getNumberOfRows(){
        return data.size();
    }
    /**
     * @return the number of columns in int
     */
    public int getNumberOfColumns(){
        return data.get(data.size()-1).size();
    }
    public String getVeriableData(int row, int column){
        return data.get(row).get(column);
    }
    /**
     * @return the data set as an ArrayList
     */
    public ArrayList getData(){
        return data;
    }
    /**
     * Prints all the data in a form of a table
     */
    public void printAlldata(){
        for(int i=0;i<getNumberOfRows();i++){
            for(int j=0;j<getNumberOfColumns();j++)
                System.out.print(data.get(i).get(j)+"\t");
            System.out.println();
        }
    }
}
