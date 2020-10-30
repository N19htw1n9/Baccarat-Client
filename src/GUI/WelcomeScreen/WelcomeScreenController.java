package GUI.WelcomeScreen;

import Client.ConnectionSocket;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import GUI.Controller;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeScreenController extends Controller {
    @FXML private TextField portNumberText;
    @FXML private TextField ipAddressText;

    public void MouseClickActionIP() {
        ipAddressText.setStyle("-fx-text-fill: black");
        ipAddressText.setFont(Font.font("System", FontWeight.NORMAL, 12));

        if ((ipAddressText.getText()).equals("IP Address"))
            ipAddressText.setText("");
    }

    public void MouseExitActionIP() {
        if ((ipAddressText.getText()).equals("")) {
            ipAddressText.setText("IP Address");
            ipAddressText.setStyle("-fx-text-fill: gray");
            ipAddressText.setFont(Font.font("System Italic", FontPosture.ITALIC, 12));
        }
    }

    public void MouseClickActionPN() {
        portNumberText.setStyle("-fx-text-fill: black");
        portNumberText.setFont(Font.font("System", FontWeight.NORMAL, 12));

        if ((portNumberText.getText()).equals("Port Number"))
            portNumberText.setText("");
    }

    public void MouseExitActionPN() {
        if ((portNumberText.getText()).equals("")) {
            portNumberText.setText("Port Number");
            portNumberText.setStyle("-fx-text-fill: gray");
            portNumberText.setFont(Font.font("System Italic", FontPosture.ITALIC, 12));
        }
    }

    public void ConnectButtonAction(Event e) throws IOException {
        System.out.println("Connect button clicked");
        String ipAddress = ipAddressText.getText();
        int portNumber = 5555;

        try {
            portNumber = Integer.parseInt(portNumberText.getText());
        } catch (Exception err) {
            System.out.println("Port number should be an integer");
            return;
        }

        connection = new ConnectionSocket(ipAddress, portNumber);
        setConnection(connection);

        Parent gameScreen = FXMLLoader.load(getClass().getResource("../GameScreen/GameScreen.fxml"));
        Scene gameScene = new Scene(gameScreen);

        Stage gameScreenWindow = (Stage) ((Node)e.getSource()).getScene().getWindow();
        gameScreenWindow.setScene(gameScene);
        gameScreenWindow.show();
    }
}
