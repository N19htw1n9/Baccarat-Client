package Client;

import java.io.IOException;
import java.net.Socket;

public class ConnectionSocket {
    private Socket socket;
    private String ip;
    private int port;

    public ConnectionSocket(String ip, int port) throws IOException {
        this.ip = ip;
        this.port = port;
        this.run();
    }

    public void run() throws IOException {
        this.socket = new Socket(this.ip, this.port);
        System.out.printf("\nConnected to %s server on port %d\n", this.socket.getLocalSocketAddress().toString(), this.socket.getLocalPort());
    }
}
