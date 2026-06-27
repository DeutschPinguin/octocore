package net.octocore.demo;

import java.io.IOException;
import java.net.Socket;
import net.octocore.network.packet.PacketBuffer;

public class Demo {
	static void main(String[] args) {
		System.out.println("Client started");

		try (
				var client = new Socket("localhost", 25565);
				var in = client.getInputStream();
				var out = client.getOutputStream()) {
			System.out.println("Connected to the server");
			var buffer = new PacketBuffer();
			buffer.writeVarInt(4).writeVarInt(0).writeInt(404);
			out.write(buffer.getArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
