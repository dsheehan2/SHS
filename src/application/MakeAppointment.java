package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*@author Digimon2000*/
public class MakeAppointment 
{  
    public static boolean ValidateAppointment(String Filepath, String DoctorsName, String Date, String Time)
    {
        String RequestedAppointment = DoctorsName + "," + Date + "," + Time;
        String currentLine;
        int N = 0;
        try
        {
            FileReader fr = new FileReader(Filepath);
            BufferedReader br = new BufferedReader(fr);
            
            while((currentLine = br.readLine()) != null)
            { 
                if(currentLine.contains(RequestedAppointment))
                {
                    N++;
                }
            }
            fr.close();
            br.close();
        }
        catch(Exception e)
        {
            
        }
        if (N > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public static String DoctorsSchedule(String Filepath,String DoctorsName, int PositionName, String Delimiter)
    {
        int Position = PositionName - 1;
        String currentLine;
        String data[];
        String str = "";
        ArrayList <String> holder = new ArrayList<String>();
        try
        {
            FileReader fr = new FileReader(Filepath);
            BufferedReader br = new BufferedReader(fr);
            
            while((currentLine = br.readLine()) != null)
            {
                data = currentLine.split(",");
                if((data[Position].equals(DoctorsName)))
                {
                	holder.add(currentLine + "\n");
                }
            }
            fr.close();
            br.close();
        }
        catch(Exception e)
        {
            e.getMessage();
        }
        
        for(int x = 0; x < holder.size(); x++)
        	str = str + holder.get(x);
        
        return str;    
    }
    
    public static void AppointmentBuilder(String DoctorsName,String PatientName, String Date, String Time)
    {
        String Appointment = PatientName + "," + Date + "," + Time + "," + DoctorsName + "\n";
        
        System.out.println(Appointment);
        
        try
        {
            String AppointmentSet = Appointment;
            
            File F1 = new File("Appointment.txt"); /*Change this filepath to the appointment filepath*/
            if(!F1.exists())
            {
                F1.createNewFile();
            }
            
            FileWriter fw = new FileWriter(F1.getName(),true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(AppointmentSet);
            bw.close();
            fw.close();
            System.out.println("Appointment creation complete for: " + AppointmentSet);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public static void RemoveAvailableTime(String Filepath,String DoctorsName,String Date, String Time)
    {
        String RequestedRemove = DoctorsName + "," + Date + "," + Time;
        String tempFile = "Temp.txt";
        File oldFile = new File(Filepath);
        File newFile = new File(tempFile);
        
        String currentLine;
        try
        {
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            
            FileReader fr = new FileReader(Filepath);
            BufferedReader br = new BufferedReader(fr);
            
            while((currentLine = br.readLine()) != null)
            {
                if(!(currentLine.equalsIgnoreCase(RequestedRemove)))
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
            System.out.println("An error has occurred !");
        }
    }
    
    public static boolean checkDoctorExists(String doctorName)
	{
		BufferedReader reader = null;
		StringBuilder stringBuilder = null;
        String line = null;
		
		try
		{
			reader = new BufferedReader(new FileReader("Doctor_Schedule.txt"));
	        stringBuilder = new StringBuilder();
	        String ls = System.getProperty("line.separator");
	        while ((line = reader.readLine()) != null) {
	            stringBuilder.append(line);
	            stringBuilder.append(ls);
	        }
	        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
	        reader.close();

	        String content = stringBuilder.toString();

			int index = content.indexOf(doctorName);
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
