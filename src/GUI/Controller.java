package GUI;

import javafx.scene.control.ToggleGroup;
import Client.ConnectionSocket;

public class Controller {
    protected ToggleGroup group;
    protected ConnectionSocket connection;

    public void setConnection(ConnectionSocket connection) {
        this.connection = connection;
    }
}
