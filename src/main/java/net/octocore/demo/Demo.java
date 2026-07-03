package net.octocore.demo;

import net.octocore.network.datatype.VarInt;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Scanner;


public class Demo
{

	static void main ()
	{
		
		try (var client = new Socket("localhost", 25565); var in = client.getInputStream(); var out = client.getOutputStream())
		{
			System.out.print("Connected to the server\n> ");
			demoFixedPacket(in, out, (byte) 0);
			demoFixedPacket(in, out, (byte) 5);
			out.write(0);
			demoFixedPacket(in, out, (byte) 10);
			demoFixedPacket(in, out, (byte) -1);
			out.write(0);
			out.write(0);
		}
		catch (IOException _) {}
	}
	
	
	private static void demoFixedPacket (InputStream in, OutputStream out, byte add)
		throws IOException
        {
		var buf = ByteBuffer.allocate(5);
		VarInt.write(buf, 4);
		VarInt.write(buf, (byte) (20 + add));
	        buf.put((byte) (10 + add));
	        buf.put((byte) (50 + add));
	        buf.put((byte) (100 + add));
		var arr = buf.array();
		out.write(arr);
		System.out.printf("sent data (size=%d,id=%d) to server: %s\n", arr.length, arr[1], Arrays.toString(arr));
	}
	
	
	private static void demoScanner (InputStream in, OutputStream out)
		throws IOException
	{
		var scanner = new Scanner(System.in);
		
		while (true)
		{
			var data = scanner.nextLine();
			
			if (data.toLowerCase().startsWith("q")) break;
			
			try
			{
				int num = Integer.parseInt(data);
				out.write(num);
				System.out.printf("Sent to the server: %d\n> ", num);
			}
			catch (NumberFormatException e)
			{
				System.err.printf("Incorrect number format: '%s'\n> ", data);
			}
		}
	}

}
