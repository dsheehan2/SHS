package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class UpdateMeasurement 
{
	private int employeeID;
	private String patientName;
	
	public UpdateMeasurement(int employee, String name)
	{
		employeeID = employee;
		patientName = name;
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
	
	public void updateVisitandMeasurements(String reason, String measurements)
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
    			ary.set(x+3, "Reason to Visit: " + reason);
    			ary.set(x+4, "Vital Signs: " + measurements);
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
	
	public String getPatientName()
    {
    	return patientName;
    }
    
    public int getEmployeeID()
    {
    	return employeeID;
    }
}
