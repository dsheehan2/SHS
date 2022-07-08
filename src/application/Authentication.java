package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Authentication
{
	private int employeeID;
	
	public Authentication(int employeeID)
	{
		this.employeeID = employeeID;
	}
	
	public boolean pinAuth (int EnteredPin) 
	{
        String fullPIN = "PIN: " + EnteredPin;
        boolean IsItMatched = false;

        /// READ FILE AND MATCH

        String str = "";
        ArrayList<String> ary = new ArrayList<String>();
        BufferedReader reader;

        try
        {
            reader = new BufferedReader(new FileReader(new File("AuthenticationPIN.txt")));

            while((str = reader.readLine()) != null)
            {
                ary.add(str);
            }
        }
        catch(FileNotFoundException e) {e.getMessage();}
        catch(IOException e) {e.getMessage();}

        for(int x = 0; x < ary.size(); x++)
        {
            if(ary.get(x).contains(Integer.toString(employeeID).trim()))
            {
                if(ary.get(x+1).equals(fullPIN)) {
                    IsItMatched = true;
                }
            }
        }
       return IsItMatched;
    }
}
