package net.octocore.network.packet.handshake.data;

public enum ConnectionIntent
{
        UNKNOWN, STATUS, LOGIN, TRANSFER;
        
        
        public static ConnectionIntent from (final int id)
        {
                return switch (id)
                {
                        case 1 -> STATUS;
                        case 2 -> LOGIN;
                        case 3 -> TRANSFER;
                        default -> UNKNOWN;
                };
        }
        
}
