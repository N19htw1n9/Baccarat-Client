package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.lang.Thread;
import java.util.function.Consumer;

public class ConnectionSocket extends Thread {
    private Socket socket;
    private String ip;
    private int port;
    private Consumer<Serializable> callback;

    public ConnectionSocket(Consumer<Serializable> callback, String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.callback = callback;

        this.run();
    }

    public void run() {
        try {
            this.socket = new Socket(this.ip, this.port);
        } catch (IOException e) {
            System.out.printf("\n\nError, could not connect to server %s port %d", this.ip, this.port);
            return;
        }
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
