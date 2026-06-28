package net.octocore.network.packet.handshake.serverbound;

public enum ClientIntent {
	UNKNOWN, STATUS, LOGIN, TRANSFER;

	public static ClientIntent getIntent(byte intentId) {
		return switch (intentId) {
			case 1 -> STATUS;
			case 2 -> LOGIN;
			case 3 -> TRANSFER;
			default -> UNKNOWN;
		};
	}

	public boolean checkKnownIntent(ClientIntent intent) {
		return intent != UNKNOWN;
	}
}
