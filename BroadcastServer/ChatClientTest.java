import java.net.*;
import java.io.*;

class ChatClientTest
{
	public static void main(String str[])
	{
		try
		{
			Socket sk= new Socket("192.168.1.102",1500);
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

			System.out.println("Enter Name: ");
			String name=br.readLine();

			DataOutputStream dout= new DataOutputStream(sk.getOutputStream());
			dout.writeUTF("login#"+name);
			dout.flush();

			new ReaderThread(sk);

			System.out.println("Enter msgs, enter 'stop' to terminate: ");

			while(true)
			{
				String msg=br.readLine();
				if(msg.equalsIgnoreCase("stop"))
				{
					dout.writeUTF("logout#"+name);
					dout.flush();
					break;
				}
				msg="message#"+name+": "+msg;
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


class ReaderThread extends Thread
{
	Socket sk;
	ReaderThread(Socket sk)
	{
		this.sk=sk;
		setDaemon(true);
		start();
	}

	public void run()
	{
		try
		{
			while(true)
			{
				DataInputStream din= new DataInputStream(sk.getInputStream());
				String msg=din.readUTF();
				System.out.println(msg);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}