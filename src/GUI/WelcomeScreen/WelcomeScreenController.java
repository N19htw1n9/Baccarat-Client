package GUI.WelcomeScreen;

import BaccaratGame.ConnectionSocket;
import javafx.application.Platform;
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

    public void mouseClickActionIP() {
        ipAddressText.setStyle("-fx-text-fill: black");
        ipAddressText.setFont(Font.font("System", FontWeight.NORMAL, 14));

        if ((ipAddressText.getText()).equals("IP Address"))
            ipAddressText.setText("");
    }

    public void mouseExitActionIP() {
        if ((ipAddressText.getText()).equals("")) {
            ipAddressText.setText("IP Address");
            ipAddressText.setStyle("-fx-text-fill: gray");
            ipAddressText.setFont(Font.font("System Italic", FontPosture.ITALIC, 14));
        }
    }

    public void mouseClickActionPN() {
        portNumberText.setStyle("-fx-text-fill: black");
        portNumberText.setFont(Font.font("System", FontWeight.NORMAL, 14));

        if ((portNumberText.getText()).equals("Port Number"))
            portNumberText.setText("");
    }

    public void mouseExitActionPN() {
        if ((portNumberText.getText()).equals("")) {
            portNumberText.setText("Port Number");
            portNumberText.setStyle("-fx-text-fill: gray");
            portNumberText.setFont(Font.font("System Italic", FontPosture.ITALIC, 14));
        }
    }

    public void connectButtonAction(Event e) throws IOException {
        System.out.println("Connect button clicked");
        String ipAddress = ipAddressText.getText();
        int portNumber = 5555;

        try {
            portNumber = Integer.parseInt(portNumberText.getText());
        } catch (Exception err) {
            System.out.println("Port number should be an integer");
            return;
        }

        connection = new ConnectionSocket(data -> {
            Platform.runLater(() -> {
                System.out.println(data);
            });
        }, ipAddress, portNumber);

        Parent gameScreen = FXMLLoader.load(getClass().getResource("../GameScreen/GameScreen.fxml"));
        Scene gameScene = new Scene(gameScreen);

        Stage window = (Stage) ((Node)e.getSource()).getScene().getWindow();
        window.setScene(gameScene);
        window.setTitle("Play game");
        window.show();
    }
}
