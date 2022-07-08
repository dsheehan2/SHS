package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

public class ClearNoShow 
{

	/*
	 * To change this license header, choose License Headers in Project Properties.
	 * To change this template file, choose Tools | Templates
	 * and open the template in the editor.
	 */
	/*@author Digimon2000*/
	   
	public static void RemoveNoShow(String Filepath, String RemovePatient, int PositionPatientName, String Delimiter)
	{
	    int Position = PositionPatientName - 1;
	    String tempFile = "Temp.txt";
	    File oldFile = new File(Filepath);
	    File newFile = new File(tempFile);
	        
	    String currentLine;
	    String data[];
	    try
	    {
	        FileWriter fw = new FileWriter(tempFile, true);
	        BufferedWriter bw = new BufferedWriter(fw);
	        PrintWriter pw = new PrintWriter(bw);
	            
	        FileReader fr = new FileReader(Filepath);
	        BufferedReader br = new BufferedReader(fr);
	            
	        while((currentLine = br.readLine()) != null)
	        {
	            data = currentLine.split(Delimiter);
	            if(!(data[Position].equalsIgnoreCase(RemovePatient)))
	            {
	                pw.println(currentLine);
	            }
	        }
	        pw.flush();
	        pw.close();
	        fr.close();
	        br.close();
	        bw.close();
	            
	        oldFile.delete();
	        File dump = new File(Filepath);
	        newFile.renameTo(dump);
	            
	     }
	     catch(Exception e)
	     {
	         System.out.println("An error has occured!");
	     }
	 }
	    
	 public static void AddNameToNoShow(String PatientName)
	 {
	     try
	     {
	         String Name = PatientName + "\n";
	         File F1 = new File("No_Show.txt");
	         if(!F1.exists())
	         {
	             F1.createNewFile();
	         }
	            
	         FileWriter fw = new FileWriter(F1.getName(),true);
	         BufferedWriter bw = new BufferedWriter(fw);
	         bw.write(Name);
	         bw.close();
	         fw.close();
	         System.out.println(Name + "Added to No Show List");
	     }
	     catch(IOException e)
	     {
	         e.printStackTrace();
	     }
	 }
	 
	 public static boolean checkPatientExists(String patientName)
		{
			BufferedReader reader = null;
			StringBuilder stringBuilder = null;
	        String line = null;
			
			try
			{
				reader = new BufferedReader(new FileReader("Appointment.txt"));
		        stringBuilder = new StringBuilder();
		        String ls = System.getProperty("line.separator");
		        while ((line = reader.readLine()) != null) {
		            stringBuilder.append(line);
		            stringBuilder.append(ls);
		        }
		        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		        reader.close();

		        String content = stringBuilder.toString();

				int index = content.indexOf(patientName);
				if(index == -1) 
					return false;	
				else 
					return true;
			}
		        catch(FileNotFoundException e) {System.out.println("File not Found given: " + e.getMessage());}
		        catch(Exception e){System.out.println("Other Error Occured: " + e.getMessage());}
			
			return false;		
		}
	    
}

