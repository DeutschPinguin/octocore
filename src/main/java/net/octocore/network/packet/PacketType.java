package net.octocore.network.packet;

import java.util.Arrays;
import java.util.function.Function;

import net.octocore.network.datatype.DataBuffer;


public class PacketType<T extends PacketData>
{
        private static PacketType<?>[] TYPES = new PacketType[10];
        private static int REGISTERED_COUNT = 0;
        
        protected final Function<DataBuffer, T> creator;
        
        
        public PacketType (Function<DataBuffer, T> creator)
        {
                this.creator = creator;
        }
        
        
        public static PacketType<?> get (int id)
        {
                if (id < 0 || TYPES.length <= id) return null;
                return TYPES[id];
        }
        
        
        public static <T extends PacketData> int register (PacketType<T> type)
        {
                if (type == null) return -1;
                
                if (TYPES.length <= REGISTERED_COUNT)
                {
                        TYPES = Arrays.copyOf(TYPES, TYPES.length * 2);
                }
                
                int id = REGISTERED_COUNT++;
                TYPES[id] = type;
                return id;
        }
        
        
        public Function<DataBuffer, T> getCreator ()
        {
                return this.creator;
        }
        
        
        public final T create (DataBuffer buffer)
        {
                return this.getCreator().apply(buffer);
        }
        
        
        public int getSizeLimit ()
        {
                return Integer.MAX_VALUE;
        }
        
}
