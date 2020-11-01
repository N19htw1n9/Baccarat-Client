package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    public BaccaratInfo send(int bid, String hand) throws IOException, ClassNotFoundException {
        ObjectOutputStream out = new ObjectOutputStream(this.socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(this.socket.getInputStream());
        this.socket.setTcpNoDelay(true);

        BaccaratInfo req = new BaccaratInfo(bid, hand);
        out.writeObject(req);
        return (BaccaratInfo) in.readObject();
    }

    public Socket getSocket() {
        return socket;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }
}
