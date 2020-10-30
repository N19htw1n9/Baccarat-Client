package GUI;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import Client.ConnectionSocket;
import javafx.stage.Stage;

public class Controller
{
    @FXML private TextField portNumberText;
    @FXML private TextField ipAddressText;
    @FXML private TextField playerBidText;
    @FXML private ToggleButton playerToggleButton;
    @FXML private ToggleButton bankerToggleButton;
    @FXML private AnchorPane anchor;

    private ToggleGroup group;


    public void MouseClickActionIP()
    {
        ipAddressText.setStyle("-fx-text-fill: black");
        ipAddressText.setFont(Font.font("System", FontWeight.NORMAL, 12));

        if((ipAddressText.getText()).equals("IP Address"))
            ipAddressText.setText("");
    }

    public void MouseClickActionPN()
    {
        portNumberText.setStyle("-fx-text-fill: black");
        portNumberText.setFont(Font.font("System", FontWeight.NORMAL, 12));

        if((portNumberText.getText()).equals("Port Number"))
            portNumberText.setText("");
    }

    public void MouseExitActionIP()
    {
        if((ipAddressText.getText()).equals(""))
        {
            ipAddressText.setText("IP Address");
            ipAddressText.setStyle("-fx-text-fill: gray");
            ipAddressText.setFont(Font.font("System Italic", FontPosture.ITALIC, 12));
        }
    }

    public void MouseExitActionPN()
    {
        if((portNumberText.getText()).equals(""))
        {
            portNumberText.setText("Port Number");
            portNumberText.setStyle("-fx-text-fill: gray");
            portNumberText.setFont(Font.font("System Italic", FontPosture.ITALIC, 12));
        }
    }

    public void MouseClickActionPB()
    {
        playerBidText.setStyle("-fx-text-fill: black");
        playerBidText.setFont(Font.font("System", FontWeight.NORMAL, 16));

        if((playerBidText.getText()).equals("Player Bid"))
            playerBidText.setText("");
    }

    public void MouseExitActionPB()
    {
        if((playerBidText.getText()).equals(""))
        {
            playerBidText.setText("Player Bid");
            playerBidText.setStyle("-fx-text-fill: gray");
            playerBidText.setFont(Font.font("System Italic", FontPosture.ITALIC, 16));
        }
    }

    public void ConnectButtonAction(Event e) {
        System.out.println("Connect button clicked");
        String ipAddress = ipAddressText.getText();

        try {
            int portNumber = Integer.parseInt(portNumberText.getText());
            ConnectionSocket connection = new ConnectionSocket(ipAddress, portNumber);

            Parent gameScreen = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));
            Scene gameScene = new Scene(gameScreen);

            Stage gameScreenWindow = (Stage) ((Node)e.getSource()).getScene().getWindow();
            gameScreenWindow.setScene(gameScene);
            gameScreenWindow.show();
        } catch (Exception err) {
            System.out.println("Port number should be an integer");
        }
    }

    public void toggleButton()
    {
        group = new ToggleGroup();
        playerToggleButton.setToggleGroup(group);
        bankerToggleButton.setToggleGroup(group);

        if(playerToggleButton.isSelected())
        {
            playerToggleButton.setStyle("-fx-background-color: #ed7117");
            bankerToggleButton.setStyle("-fx-background-color: orange");
        }

        else if(bankerToggleButton.isSelected())
        {
            bankerToggleButton.setStyle("-fx-background-color: #ed7117");
            playerToggleButton.setStyle("-fx-background-color: orange");
        }

        else if(!(bankerToggleButton.isSelected() || playerToggleButton.isSelected()))
        {
            bankerToggleButton.setStyle("-fx-background-color: orange");
            playerToggleButton.setStyle("-fx-background-color: orange");
        }

    }
}
