package net.octocore.networking;

import net.octocore.datatype.VarInt;
import net.octocore.datatype.DataBuffer;
import net.octocore.networking.packet.PacketType;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;


public class Server
{
        public static final int MAX_CLIENT_TIME_OUT = 2200; // in millis
        
        protected final ServerSocket socket;
        
        
        protected Server (ServerSocket socket)
        {
                this.socket = socket;
        }
        
        
        public static Server open (int port)
                throws IOException
        {
                return new Server(new ServerSocket(port));
        }
        
        
        public void run ()
        {
                System.out.println("Server started");
                
                while (true) {
                        try (
                                var client = this.socket.accept();
                                var in = client.getInputStream();
                                var out = client.getOutputStream()
                        )
                        {
                                this.handleSingleConnection(client, in, out);
                        }
                        catch (SocketTimeoutException e)
                        {
                                System.out.println("Timed out");
                        }
                        catch (IOException e)
                        {
                                e.printStackTrace();
                        }
                }
        }
        
        
        protected void handleSingleConnection (Socket client, InputStream in, OutputStream out)
                throws IOException
        {
                client.setSoTimeout(MAX_CLIENT_TIME_OUT);
                
                while (true)
                {
                        int size = this.loadVarInt(in), id = this.loadVarInt(in);
                        var type = PacketType.get(id);
                        
                        if (type == null)
                        {
                                System.out.println("Received packet with unknown identifier");
                                return;
                        }
                        if (type.getSizeLimit() < size)
                        {
                                System.out.println("Received packet that exceeds its size limits");
                                return;
                        }
                        if (size < 1) continue;
                        
                        var buf = new DataBuffer();
                        var arr = new byte[1];
                        
                        while (in.read(arr) != -1 && buf.getSize() <= size)
                                buf.writeByte(arr[0]);
                
                        type.create(buf.flip());
                }
        }
        
        
        protected int loadVarInt (InputStream in)
                throws IOException
        {
                var buf = new DataBuffer();
                var arr = new byte[1];
                var i = 0;
                
                while (in.read(arr) != -1)
                {
                        var value = arr[0];
                        buf.writeByte(value);
                        if (++i >= 5 || !VarInt.hasNextByte(value)) return buf.flip().readVarInt();
                }
                
                in.close();
                return -1;
        }
        
}
