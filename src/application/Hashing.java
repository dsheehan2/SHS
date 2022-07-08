package application;

import java.security.MessageDigest;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Hashing 
{
		public static byte[] generate(String msg) throws Exception
		{
		   MessageDigest md = MessageDigest.getInstance("SHA-1");
		   byte[] message = msg.getBytes();
		   md.update(message);
		   byte[] mdbytes = md.digest();
		   return(mdbytes);
		}
		
		public static boolean verify(byte[] hashValue, String msg) throws Exception
	    {
		   byte[] mdBytes = generate(msg);
		  
		   if (MessageDigest.isEqual(hashValue, mdBytes))
			   return true;
		   else
			   return false;
		}
	
}


