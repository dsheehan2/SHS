package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TreatPatient
{
	private int employeeID;
	private String patientName;

    public TreatPatient(int employeeID, String patientName)
    {
        this.employeeID = employeeID;
        this.patientName = patientName;
    }

    public boolean validateEmployeeID()
    {
    	BufferedReader read = null;
        String str, valid = "";
        try
        {
            read = new BufferedReader(new FileReader(new File("EmployeeAccount.txt")));
            while((str = read.readLine()) != null)
            {
            	if(str.contains(Integer.toString(employeeID)))
            	{
            		read.readLine(); 
            		valid = read.readLine();
            		break;
            	}
            }
            read.close();
        }
        catch(FileNotFoundException e) {System.out.println("File not Found given: " + e.getMessage());}
        catch(Exception e){System.out.println("Other Error Occured: " + e.getMessage());}
        
        if(valid.trim().equals("Granted"))
        	return true;
        else
        	return false;
    }
	
	public String showPatientMedicalInfo()
	{
		BufferedReader read = null;
    	BufferedReader prev = null;
        String str, valid2 = "", valid = "";
        try
        {
            read = new BufferedReader(new FileReader(new File("PatientMedicalChart.txt")));
            prev = new BufferedReader(new FileReader(new File("PatientMedicalChart.txt")));
            while((str = read.readLine()) != null)
            {
            	if(str.contains(patientName))
            	{
            		valid = valid2 + "\n" + str + "\n" + read.readLine() + "\n" + read.readLine() + "\n" + read.readLine() + "\n" + read.readLine() + "\n" + read.readLine() + "\n" + read.readLine();
            		break;
            	}
            	valid2 = prev.readLine();
            }
            read.close();
            prev.close();
        }
        catch(FileNotFoundException e) {System.out.println("File not Found given: " + e.getMessage());}
        catch(Exception e){System.out.println("Other Error Occured: " + e.getMessage());}
    	
        
        if(valid.equals(""))
        	return null;
        else
        	return valid;	
	}
	
	public void updateTreatmentandPrescription(String treatment, String perscription)
	{
		String str = "";
    	ArrayList<String> ary = new ArrayList<String>();
    	BufferedReader reader;
    	PrintWriter writer;
    	
    	try
    	{
    		reader = new BufferedReader(new FileReader(new File("PatientMedicalChart.txt")));
    		
    		while((str = reader.readLine()) != null)
    		{
    			ary.add(str);
    		}
    		
    	}
    	catch(FileNotFoundException e) {e.getMessage();}
    	catch(IOException e) {e.getMessage();}
    	
    	for(int x = 0; x < ary.size(); x++)
    	{
    		if(ary.get(x).contains(patientName))
    		{
    			ary.set(x+5, "Treatment Content: " + treatment);
    			ary.set(x+6, "Prescription: " + perscription);
    		}
    	}
    	
    	try
    	{
    		writer = new PrintWriter(new File("PatientMedicalChart.txt"));
    		
    		for(int x = 0; x < ary.size(); x++)
    			writer.print(ary.get(x) + "\n");
    		
    		writer.close();
    	}
    	catch(FileNotFoundException e) {e.getMessage();}
	}
	
	//SECURITY: If Patient has existing treatment, it will return true, if Patient is not in file it will return true, if patient has no treatment/prescription, it returns false.
	public boolean checkExistingTreatement()
	{
		String str = "";
    	ArrayList<String> ary = new ArrayList<String>();
    	BufferedReader reader;
    	PrintWriter writer;
    	
    	try
    	{
    		reader = new BufferedReader(new FileReader(new File("PatientMedicalChart.txt")));
    		
    		while((str = reader.readLine()) != null)
    		{
    			ary.add(str);
    		}
    		
    	}
    	catch(FileNotFoundException e) {e.getMessage();}
    	catch(IOException e) {e.getMessage();}
    	
    	for(int x = 0; x < ary.size(); x++)
    	{
    		if(ary.get(x).contains(patientName))
    		{
    			if((ary.get(x+5).trim().equals("Treatment Content:")) && (ary.get(x+6).trim().equals("Prescription:")))
    				return true;
    			else
    				return false;
    		}			
    	}
    	return true;
	}
	
	public boolean checkPatientExists()
	{
		BufferedReader reader = null;
		StringBuilder stringBuilder = null;
        String line = null;
		
		try
		{
			reader = new BufferedReader(new FileReader("PatientMedicalChart.txt"));
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
	
	public String getPatientName()
    {
    	return patientName;
    }
    
    public int getEmployeeID()
    {
    	return employeeID;
    }

}
  

