import java.io.*;
import java.net.*;
import java.util.Scanner;



class Client 
{
	public static void main(String[] s) 
	{
		try
		{
			Socket c = new Socket("localhost", 3337);
			String msg = "";			
			DataOutputStream dout = new DataOutputStream(c.getOutputStream());

			while(!msg.equalsIgnoreCase("bye")) 
			{

			Scanner sc = new Scanner(System.in);
			msg = sc.nextLine();
			dout.writeUTF(msg);
			dout.flush();
			}
			dout.close();
			c.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
