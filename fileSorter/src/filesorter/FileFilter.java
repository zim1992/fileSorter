/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesorter;
import java.util.ArrayList;

/**
 *
 * @author Corjan
 */
public class FileFilter {
    private ArrayList<ArrayList<String>> inputData = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<String>> filteredData = new ArrayList<ArrayList<String>>();
    private ArrayList<Integer> rows = new ArrayList<Integer>();
    private ArrayList<Integer> columns = new ArrayList<Integer>();
    
    private String rowFilter;
    private String columnFilter;
    /**
     * 
     * @param data this is a two dimensional ArrayList of strings
     * @param rowFilter this is a string containing the list of rows to be printed out
     * @param columnFilter this is a string containing the list of columns wished to be contained in the filtered data
     */
    public FileFilter(ArrayList<ArrayList<String>> data,String rowFilter, String columnFilter){
        inputData = data;
        this.rowFilter = rowFilter;
        this.columnFilter = columnFilter;
        // After the data has been added the filtering method is invoked
        filterInputData();
    }
    /**
     * This method takes the data from the ArrayList containing all the information and list of rows and columns and filters it into a new filtered list array
     */
    private void filterInputData(){
        char[] rowFilterChar = rowFilter.toCharArray();
        char[] columnFilterChar = columnFilter.toCharArray();
        int oldInt=0;
        int i;
        //Converts the String containing all the Columns into a list array 
        for(i=0;i<columnFilter.length();i++){
            if(columnFilterChar[i]==44){
                if(oldInt==0){
                    try{
                         columns.add(Integer.parseInt(columnFilter.substring(oldInt,i)));
                    }catch(Exception e){
                        System.err.println("ERROR: You have placed a column value which is not an value \""+columnFilter.substring(oldInt,i)+"\"");
                    }
                    oldInt=i;                    
                }else{
                    try{
                        columns.add(Integer.parseInt(columnFilter.substring(oldInt+1,i)));
                    }catch(Exception e){
                        System.err.println("ERROR: You have placed a column value which is not an value \""+columnFilter.substring(oldInt+1,i)+"\"");
                    }
                    oldInt=i;
                }                              
            }                
        }
        try{
            columns.add(Integer.parseInt(columnFilter.substring(oldInt+1)));
        }catch(Exception e){
            System.err.println("ERROR: You have placed a column value which is not an value \""+columnFilter.substring(oldInt+1)+"\"");
        }
        //Converts the String containing all the rows into a list array 
        oldInt=0;
        for(i=0;i<rowFilter.length();i++){
            if(rowFilterChar[i]==44){
                if(oldInt==0){
                    try{
                         rows.add(Integer.parseInt(rowFilter.substring(oldInt,i),10));                        
                    }catch(Exception e){
                        System.err.println("ERROR: You have placed a row value which is not an value \""+rowFilter.substring(oldInt,i)+"\"");
                    }            
                    
                    oldInt=i;                    
                }else{
                    try{
                        rows.add(Integer.parseInt(rowFilter.substring(oldInt+1,i),10));                        
                    }catch(Exception e){
                        System.err.println("ERROR: You have placed a row value which is not an value \""+rowFilter.substring(oldInt+1,i)+"\"");
                    }
                    oldInt=i;
                }                              
            }                
        }
        try{
            rows.add(Integer.parseInt(rowFilter.substring(oldInt+1),10));                        
        }catch(Exception e){
            System.err.println("ERROR: You have placed a row3 value which is not an value \""+rowFilter.substring(oldInt+1,i)+"\"");
        }
        filteredData.add(new ArrayList<String>());
        filteredData.get(filteredData.size()-1).add(inputData.get(0).get(0));
        for(i=0;i<columns.size();i++){
            if(columns.get(i)<inputData.get(inputData.size()-1).size()){
                filteredData.get(filteredData.size()-1).add(inputData.get(0).get(columns.get(i)));
            }
        }
        //This is what takes the information from the arraylists and creates the filtered data. The if statements are designed to filter out all the values written which do not fall in the domain of the table.
        for(i=0;i<rows.size();i++){
            // filters out all the row values which do not fall in the domain of the data set
            if(rows.get(i)<=inputData.size()){
                filteredData.add(new ArrayList<String>());
                filteredData.get(filteredData.size()-1).add("OURID"+i);
                for(int j=0;j<columns.size();j++){
                    if(columns.get(j)<=inputData.get(inputData.size()-1).size()){
                        filteredData.get(filteredData.size()-1).add(inputData.get(rows.get(i)).get(columns.get(j)));
                    }
                }   
            }  
        }
        
    }
   public void printFilteredData(){
       for(int i=0;i<filteredData.size();i++){
            for(int j=0;j<filteredData.get(i).size();j++)
                System.out.print(filteredData.get(i).get(j)+"\t");
            System.out.println();
        }       
   }
    
}
