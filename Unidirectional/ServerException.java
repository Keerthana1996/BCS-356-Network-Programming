import java.io.*;
import java.net.*;


class MyThread extends Thread
{

String msg;
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public void run()
	{	
	  try
	   {
		excp();
           }
   	  catch(IOException e)
	  {
		e.printStackTrace();
 	  }
	}

	void excp() throws IOException
	{
		msg = br.readLine();
			if(!msg.equals("")) 
			{
				try 
				{
					throw new IOException();
				}
				catch(IOException e) 
				{
					System.out.println("Not allowed to send from server, only client can send!");
				}
			}	
	}

} 



class Server {

	public static void main(String[] s) 
	{
		MyThread th=new MyThread();
		th.start();
		try
		{
			ServerSocket ss = new ServerSocket(3337);
			Socket c = ss.accept();	
						
			DataInputStream din = new DataInputStream(c.getInputStream());
			String str="";
			do{
			
			str=din.readUTF();
			
			System.out.println(""+str);
			}while(!str.equalsIgnoreCase("bye"));

			din.close();
			ss.close();

		}catch(Exception e) {
			e.printStackTrace();			
		}

	}
	
}
