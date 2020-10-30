package GUI;

import Client.ConnectionSocket;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Controller {
    @FXML private TextField portNumberText;
    @FXML private TextField ipAddressText;
    @FXML private Button connectButton;


    public void MouseClickActionIP() {
        ipAddressText.setStyle("-fx-text-fill: black");
        ipAddressText.setFont(Font.font("System", FontWeight.NORMAL, 12));

        if ((ipAddressText.getText()).equals("IP Address"))
            ipAddressText.setText("");
    }

    public void MouseClickActionPN() {
        portNumberText.setStyle("-fx-text-fill: black");
        portNumberText.setFont(Font.font("System", FontWeight.NORMAL, 12));

        if ((portNumberText.getText()).equals("Port Number"))
            portNumberText.setText("");

    }

    public void MouseExitActionIP() {
        if ((ipAddressText.getText()).equals("")) {
            ipAddressText.setText("IP Address");
            ipAddressText.setStyle("-fx-text-fill: gray");
            ipAddressText.setFont(Font.font("System Italic", FontPosture.ITALIC, 12));
        }
    }

    public void MouseExitActionPN() {
        if ((portNumberText.getText()).equals("")) {
            portNumberText.setText("Port Number");
            portNumberText.setStyle("-fx-text-fill: gray");
            portNumberText.setFont(Font.font("System Italic", FontPosture.ITALIC, 12));
        }
    }

    public void ConnectButtonAction(Event e) {
        System.out.println("Connect button clicked");
        String ipAddress = ipAddressText.getText();

        try {
            int portNumber = Integer.parseInt(portNumberText.getText());
            ConnectionSocket connection = new ConnectionSocket(ipAddress, portNumber);
        } catch (Exception err) {
            System.out.println("Port number should be an integer");
        }
    }
}
