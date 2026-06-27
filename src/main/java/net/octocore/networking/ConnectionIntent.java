package net.octocore.networking;

public enum ConnectionIntent
{
        UNKNOWN, STATUS, LOGIN(true), TRANSFER(true);
        
        
        private final boolean login;
        
        
        ConnectionIntent (final boolean login)
        {
                this.login = login;
        }
        
        
        ConnectionIntent ()
        {
                this(false);
        }
        
        
        public static ConnectionIntent from (final int number)
        {
                return switch (number)
                {
                        case 1 -> STATUS;
                        case 2 -> LOGIN;
                        case 3 -> TRANSFER;
                        default -> UNKNOWN;
                };
        }
        
        
        public int getId ()
        {
                return this.ordinal();
        }
        
        
        public boolean isToLogin ()
        {
                return this.login;
        }
        
}
