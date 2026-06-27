package net.octocore.datatype;

import java.nio.ByteBuffer;


public class VarLong
{
        
        public static boolean hasNextByte (final byte value)
        {
                return (value & 128) == 128;
        }
        
        
        public static int getByteSize (final long value)
        {
                for (int i = 1; i < 10; ++i)
                {
                        if ((value & -1L << i * 7) == 0L) return i;
                }
                
                return 10;
        }
        
        
        public static long read (ByteBuffer buffer)
        {
                long value = 0;
                int i = 0;
                byte b;
                
                do
                {
                        b = buffer.get();
                        value |= (b & 127L) << i++ * 7;
                }
                while (i <= 10 && VarLong.hasNextByte(b));
                
                return value;
        }
        
        
        public static void write (ByteBuffer buffer, long value)
        {
                while ((value & -128L) != 0L)
                {
                        buffer.put((byte) (value & 127L | 128));
                        value >>>= 7;
                }
                
                buffer.put((byte) value);
        }

}
