package net.octocore;

import net.octocore.network.Server;

public class Main {
	public static void main(String[] args) {
		// if (TestPacket.ID == 0);

		try {
			var server = Server.open(25565);
			server.run();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
