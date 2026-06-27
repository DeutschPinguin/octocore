package net.octocore.datatype;

import java.nio.ByteBuffer;


public class VarInt
{
        
        public static boolean hasNextByte (final byte value)
        {
                return (value & 128) == 128;
        }
        
        
        public static int getByteSize (final int value)
        {
                for (int i = 1; i < 5; ++i)
                {
                        if ((value & -1 << i * 7) == 0) return i;
                }
                
                return 5;
        }
        
        
        public static int read (ByteBuffer buffer)
        {
                int value = 0, i = 0;
                byte b;
                
                do
                {
                        b = buffer.get();
                        value |= (b & 127) << i++ * 7;
                }
                while (i <= 5 && VarInt.hasNextByte(b));
                
                return value;
        }
        
        
        public static void write (ByteBuffer buffer, int value)
        {
                while ((value & -128) != 0)
                {
                        buffer.put((byte) (value & 127 | 128));
                        value >>>= 7;
                }
                
                buffer.put((byte) value);
        }

}
