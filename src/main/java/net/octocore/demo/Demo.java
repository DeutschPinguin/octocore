package net.octocore.demo;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class Demo
{

	static void main ()
	{
		
		try (var client = new Socket("localhost", 25565); var in = client.getInputStream(); var out = client.getOutputStream())
		{
			System.out.print("Connected to the server\n> ");
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
		catch (IOException _) {}
	}

}
