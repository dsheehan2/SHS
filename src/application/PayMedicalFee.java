package application;

import java.util.*;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import java.io.*;

public class PayMedicalFee
{
    private String patientName;
    private int employeeID;

    public PayMedicalFee(int employeeID, String patientName)
    {
    	this.patientName = patientName;
    	this.employeeID = employeeID;
    }
    
    //done 1st
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
    
    //done 2nd
    public String showPatientPaymenmtInfo()
    {
    	BufferedReader read = null;
    	BufferedReader prev = null;
        String str, valid2 = "", valid = "";
        try
        {
            read = new BufferedReader(new FileReader(new File("PaymentInformation.txt")));
            prev = new BufferedReader(new FileReader(new File("PaymentInformation.txt")));
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
    
    public void payByCash(int amount) 
    {
    	String str = "";
    	ArrayList<String> ary = new ArrayList<String>();
    	BufferedReader reader;
    	PrintWriter writer;
    	
    	try
    	{
    		reader = new BufferedReader(new FileReader(new File("PaymentInformation.txt")));
    		
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
    			ary.set(x+2, "Amount: $" + amount);
    			ary.set(x+3, "Payment Type: Cash");
    			ary.set(x+4, "PIN: N/A");
    		}
    	}
    	
    	try
    	{
    		writer = new PrintWriter(new File("PaymentInformation.txt"));
    		
    		for(int x = 0; x < ary.size(); x++)
    			writer.print(ary.get(x) + "\n");
    		
    		writer.close();
    	}
    	catch(FileNotFoundException e) {e.getMessage();}
     }
    
    public void payByDebit(int amount, int pinNumber, String type, String debitNumber, String referenceNum)
    {
    	String str = "";
    	ArrayList<String> ary = new ArrayList<String>();
    	BufferedReader reader;
    	PrintWriter writer;
    	
    	try
    	{
    		reader = new BufferedReader(new FileReader(new File("PaymentInformation.txt")));
    		
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
    			ary.set(x+2, "Amount: $" + amount);
    			ary.set(x+3, "Payment Type: Debit " + type + " " + encrypter(debitNumber));
    			ary.set(x+4, "PIN: " + encrypter(Integer.toString(pinNumber)));
    			referenceNum = ary.get(x+5) + " | " + referenceNum;
    			ary.set(x+5, referenceNum);
    		}
    	}
    	
    	try
    	{
    		writer = new PrintWriter(new File("PaymentInformation.txt"));
    		
    		for(int x = 0; x < ary.size(); x++)
    			writer.print(ary.get(x) + "\n");
    		
    		writer.close();
    	}
    	catch(FileNotFoundException e) {e.getMessage();}
    }
    
    public void payByCredit(int amount, String type, String creditNumber)
    {
    	String str = "";
    	ArrayList<String> ary = new ArrayList<String>();
    	BufferedReader reader;
    	PrintWriter writer;
    	
    	try
    	{
    		reader = new BufferedReader(new FileReader(new File("PaymentInformation.txt")));
    		
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
    			ary.set(x+2, "Amount: $" + amount);
    			ary.set(x+3, "Payment Type: Credit " + type + " " + encrypter(creditNumber));
    			ary.set(x+4, "PIN: N/A");
    		}
    	}
    	
    	try
    	{
    		writer = new PrintWriter(new File("PaymentInformation.txt"));
    		
    		for(int x = 0; x < ary.size(); x++)
    			writer.print(ary.get(x) + "\n");
    		
    		writer.close();
    	}
    	catch(FileNotFoundException e) {e.getMessage();}
    }
    
    
    private String encrypter(String toEncrypt)
    {
    	String cardNum2 = "";
    	try{
    		KeyGenerator keygen = KeyGenerator.getInstance("AES");
    		SecretKey key = keygen.generateKey();
    		Cipher aesCipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); 
        	byte[] cleartext = toEncrypt.getBytes();
    	   	byte[] ciphertext = Encryption.encrypt(cleartext, aesCipher, key);
    	   	cardNum2 = new String(ciphertext);
    	 	}
    		catch (Exception e){
    	 		e.printStackTrace();
    	 	}
    	return cardNum2;
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