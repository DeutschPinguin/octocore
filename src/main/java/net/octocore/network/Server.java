package net.octocore.network;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;


public final class Server
        implements Runnable, AutoCloseable
{
        private static final Logger LOGGER = LogManager.getLogger();
        private final ServerSocketChannel serverChannel;
        private final Selector selector;
        private final ByteBuffer buffer = ByteBuffer.allocate(1024);
        
        
        private Server (final ServerSocketChannel serverChannel, final Selector serverSelector)
        {
                this.serverChannel = serverChannel;
                this.selector = serverSelector;
        }
        
        
        public static Server open (final int port)
        {
                LOGGER.info("Server's starting");
                try
                {
                        var serverChannel = ServerSocketChannel.open();
                        serverChannel.bind(new InetSocketAddress(port));
                        serverChannel.configureBlocking(false);
                        var selector = Selector.open();
                        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
                        LOGGER.info("Server started on a port {}", port);
                        return new Server(serverChannel, selector);
                }
                catch (Exception e)
                {
                        LOGGER.fatal("Failed to open server socket connection: ", e);
                        throw new RuntimeException(e);
                }
        }
        
        
        private void handleConnection (final SelectionKey key)
                throws Exception
        {
                if (key.isAcceptable() && key.channel() instanceof ServerSocketChannel newChannel)
                {
                        var client = newChannel.accept();
                        client.configureBlocking(false);
                        client.register(this.selector, SelectionKey.OP_READ);
                        key.attach(new ClientConnectionContext());
                }
                
                if (key.isReadable() && key.channel() instanceof SocketChannel clientChannel)
                {
                        var client = (ClientConnectionContext) key.attachment();
                        var length = clientChannel.read(this.buffer);
                        if (length == -1) clientChannel.close();
                        else if (length < 1) return;
                        client.read(this.buffer.flip());
                        client.write(this.buffer.clear());
                        this.buffer.flip();
                        while (this.buffer.hasRemaining()) clientChannel.write(buffer);
                }
        }
        
        
        public void update ()
                throws IOException
        {
                if (this.selector.select() < 1) return;
                var iter = this.selector.selectedKeys().iterator();
                
                while (iter.hasNext())
                {
                        var key = iter.next();
                        iter.remove();
                        try
                        {
                                this.buffer.clear();
                                this.handleConnection(key);
                        }
                        catch (Exception e)
                        {
                                LOGGER.error("Dropping client connection due to the error: ", e);
                                key.channel().close();
                        }
                }
        }
        
        
        @Override
        public void run ()
        {
                try
                {
                        while (this.serverChannel.isOpen())
                        {
                                this.update();
                        }
                }
                catch (Exception e)
                {
                        LOGGER.fatal("Critical error encoutered in a server network logic: ", e);
                        throw new RuntimeException(e);
                }
        }
        
        
        @Override
        public void close ()
                throws IOException
        {
                LOGGER.info("Closing server socket connections");
                this.serverChannel.close();
                this.selector.close();
        }
        
}
