package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CheckInPatient 
{
	private String patientName;
	private String birthDate;
	private String doctorName;
	private String date;
	private int employeeID;
	
	public CheckInPatient(String name, String birthDate, int employeeID)
	{
		patientName = name;
		this.birthDate = birthDate;
		this.employeeID = employeeID;
	}
	
	public String displayAppointment()
	{
		BufferedReader read = null;
        String str, valid = "";
        try
        {
            read = new BufferedReader(new FileReader(new File("Appointment.txt")));
            while((str = read.readLine()) != null)
            {
            	if(str.contains(patientName))
            	{
            		valid = str;
            		valid.trim();
            		break;
            	}  		
            }
            read.close();
        }
        catch(FileNotFoundException e) {System.out.println("File not Found given: " + e.getMessage());}
        catch(Exception e){System.out.println("Other Error Occured: " + e.getMessage());}
    	
        
        if(valid.equals(""))
        	return null;
        else
        {
        	String finalVal = "";
        	Scanner delim = new Scanner(valid);
        	Scanner delim2 = new Scanner(valid);
        	delim.useDelimiter(",");
        	delim2.useDelimiter(",");
        	
        	while(delim.hasNext())
        		finalVal = finalVal + delim.next() + "\n";
        	
        	//setting new field values
        	patientName = delim2.next();
        	date = delim2.next() + " " + delim2.next();
        	doctorName = delim2.next();
        	
        	return finalVal;
        }
	}
	
	public boolean checkAppointment()
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
	
	public boolean checkPatientExists()
	{
		BufferedReader reader = null;
		StringBuilder stringBuilder = null;
        String line = null;
		
		try
		{
			reader = new BufferedReader(new FileReader("PatientInformation.txt"));
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
	
	public String displayPatientInformation()
	{
		BufferedReader read = null;
    	BufferedReader prev = null;
        String str, valid2 = "", valid = "";
        try
        {
            read = new BufferedReader(new FileReader(new File("PatientInformation.txt")));
            prev = new BufferedReader(new FileReader(new File("PatientInformation.txt")));
            while((str = read.readLine()) != null)
            {
            	if(str.contains(patientName))
            	{
            		valid = valid2 + "\n" + str + "\n" + read.readLine() + "\n" + read.readLine() + "\n" + read.readLine() + "\n" + read.readLine() + "\n" + read.readLine();
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
	
	//update 3 files -> PatientInformation, PaymentInformation, PatientMedicalChart
	public void updateAllFiles(String patientID, String address, String phoneNumber, String email, String SSN, String insurance)
	{
		//PatientInformation
		 String str = "";
         ArrayList<String> ary = new ArrayList<String>();
         BufferedReader reader;
         PrintWriter writer;

         try
         {
             reader = new BufferedReader(new FileReader(new File("PatientInformation.txt")));

             while((str = reader.readLine()) != null)
             {
                 ary.add(str);
             }

         }
         catch(FileNotFoundException e) {e.getMessage();}
         catch(IOException e) {e.getMessage();}

             int x = ary.size()-1;
             ary.add(x+1, "Patient ID: " + patientID);
             ary.add(x+2, "Name: " + patientName);
             ary.add(x+3, "Address: " + address);
             ary.add(x+4, "Phone Number: " + phoneNumber);
             ary.add(x+5, "Email: " + email);
             ary.add(x+6, "Social Security Number: " + SSN);
             ary.add(x+7, "Insurance Name: " + insurance);
             ary.add(x+8, "");

         try
         {
             writer = new PrintWriter(new File("PatientInformation.txt"));

             for(int y = 0; y < ary.size(); y++)
                 writer.print(ary.get(y) + "\n");

             writer.close();
         }
         catch(FileNotFoundException e) {e.getMessage();}
                 
         //PaymentInformation
         String str2 = "";
         ArrayList<String> ary2 = new ArrayList<String>();
         BufferedReader reader2;
         PrintWriter writer2;

         try
         {
             reader2 = new BufferedReader(new FileReader(new File("PaymentInformation.txt")));

             while((str2 = reader2.readLine()) != null)
             {
                 ary2.add(str2);
             }

         }
         catch(FileNotFoundException e) {e.getMessage();}
         catch(IOException e) {e.getMessage();}

             int x2 = ary2.size()-1;
             ary2.add(x2+1, "Patient ID: " + patientID);
             ary2.add(x2+2, "Name: " + patientName);
             ary2.add(x2+3, "Date: " + date);
             ary2.add(x2+4, "Amount: N/A");
             ary2.add(x2+5, "Payment Type: N/A");
             ary2.add(x2+6, "PIN: N/A");
             ary2.add(x2+7, "Reference Number: ");
             ary2.add(x2+8, "");

         try
         {
             writer2 = new PrintWriter(new File("PaymentInformation.txt"));

             for(int y = 0; y < ary2.size(); y++)
                 writer2.print(ary2.get(y) + "\n");

             writer2.close();
         }
         catch(FileNotFoundException e) {e.getMessage();}
         
         
         //PatientMedicalChart
         String str3 = "";
         ArrayList<String> ary3 = new ArrayList<String>();
         BufferedReader reader3;
         PrintWriter writer3;

         try
         {
             reader3 = new BufferedReader(new FileReader(new File("PatientMedicalChart.txt")));

             while((str3 = reader3.readLine()) != null)
             {
                 ary3.add(str3);
             }

         }
         catch(FileNotFoundException e) {e.getMessage();}
         catch(IOException e) {e.getMessage();}

             int x3 = ary3.size()-1;
             ary3.add(x3+1, "Patient ID: " + patientID);
             ary3.add(x3+2, "Patient Name: " + patientName);
             ary3.add(x3+3, "Doctor: " + doctorName);
             ary3.add(x3+4, "Date to Visit: " + date);
             ary3.add(x3+5, "Reason to Visit: ");
             ary3.add(x3+6, "Vital Signs: ");
             ary3.add(x3+7, "Treatment Content: ");
             ary3.add(x3+8, "Prescription: ");
             ary3.add(x3+9, "");

         try
         {
             writer3 = new PrintWriter(new File("PatientMedicalChart.txt"));

             for(int y = 0; y < ary3.size(); y++)
                 writer3.print(ary3.get(y) + "\n");

             writer3.close();
         }
         catch(FileNotFoundException e) {e.getMessage();}
	}
	
	public void changeAllFiles(String address, String phoneNumber, String email, String SSN, String insurance)
	{
		
		//PatientInformation
		String str = "";
        ArrayList<String> ary = new ArrayList<String>();
        BufferedReader reader;
        PrintWriter writer;

        try
        {
            reader = new BufferedReader(new FileReader(new File("PatientInformation.txt")));

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
                ary.set(x+1, "Address: " + address);
                ary.set(x+2, "Phone Number: " + phoneNumber);
                ary.set(x+3, "Email: " + email);
                ary.set(x+4, "Social Security Number: " + SSN);
                ary.set(x+5, "Insurance Name: " + insurance);
            }
        }

        try
        {
            writer = new PrintWriter(new File("PatientInformation.txt"));

            for(int x = 0; x < ary.size(); x++)
                writer.print(ary.get(x) + "\n");

            writer.close();
        }
        catch(FileNotFoundException e) {e.getMessage();}
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


	public void delApp() 
	{
	    String str = "";
	    ArrayList<String> ary = new ArrayList<String>();
	    BufferedReader reader;
	    PrintWriter writer;
	
	    try
	    {
	        reader = new BufferedReader(new FileReader(new File("Appointment.txt")));
	
	        while((str = reader.readLine()) != null)
	            ary.add(str);
	    }
	    catch(FileNotFoundException e) {e.getMessage();}
	    catch(IOException e) {e.getMessage();}
	    
	    for(int x = 0; x < ary.size(); x++)
	    {
	        if(ary.get(x).contains(patientName))
	            ary.remove(x); 
	    }
	
	    try
	    {
	        writer = new PrintWriter(new File("Appointment.txt"));
	
	        for(int x = 0; x < ary.size(); x++)
	            writer.print(ary.get(x) + "\n");
	
	        writer.close();
	    }
	    catch(FileNotFoundException e) {e.getMessage();}
	}
}