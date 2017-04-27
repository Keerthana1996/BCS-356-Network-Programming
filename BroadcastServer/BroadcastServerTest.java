import java.net.*;
import java.util.*;
import java.io.*;

class BroadcastServerTest
{
	static ArrayList sockets;
	public static void main(String str[])
	{
		try
		{
			sockets=new ArrayList();
			ServerSocket ss=new ServerSocket(1500);

			while(true)
			{
				Socket client=ss.accept();
				//adding the client socket to collection
				sockets.add(client);
				//creating new thread for connect client
				new ClientThread(client);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

class ClientThread extends Thread
{
	Socket client;
	ClientThread(Socket client)
	{
		this.client=client;
		start();
	}

	public void run()
	{
		try
		{
			DataInputStream din=new DataInputStream(client.getInputStream());
			while(true)
			{
				String msg=din.readUTF();
				String tokens[]=msg.split("#");
				if(tokens[0].equals("login"))
				{
					msg=tokens[1]+" loggen in";
				}
				else if(tokens[0].equals("logout"))
				{
					msg=tokens[1]+" logged out";
				}
				else
				{
					msg=tokens[1];
				}

				System.out.println(msg);
				broadcast(msg);
				if(tokens[0].equals("logout"))
				{
					BroadcastServerTest.sockets.remove(client);
					break;
				}
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	void broadcast(String msg)
	{
		try
		{
			for(Object ob:BroadcastServerTest.sockets)
			{
				Socket client=(Socket)ob;
				DataOutputStream dout=new DataOutputStream(client.getOutputStream());
				dout.writeUTF(msg);
				dout.flush();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
