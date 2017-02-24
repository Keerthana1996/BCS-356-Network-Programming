import java.net.*;
import java.io.*;
class Client{
	public static void main(String h[]) throws Exception
	{
		Socket s=new Socket("localhost",3333);
		//Socket s= ss.accept;
		DataInputStream din= new DataInputStream(s.getInputStream());
		DataOutputStream dout =new DataOutputStream(s.getOutputStream());
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String str="", str2="";
		while(!str2.equalsIgnoreCase("Bye"))
		{	
			str2=br.readLine();
			dout.writeUTF(str2);	
			dout.flush();		
			str=din.readUTF();
			System.out.println(str);
			
			
		}
 		dout.close();
		s.close();
		//ss.close();


	}

}
