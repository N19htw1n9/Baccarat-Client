package GUI.GameScreen;

import Client.BaccaratInfo;
import GUI.Controller;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

public class GameScreenController extends Controller {
    @FXML private TextField playerBidText;
    @FXML private ToggleButton playerToggleButton;
    @FXML private ToggleButton bankerToggleButton;
    @FXML private HBox BankerHBox;
    @FXML private HBox PlayerHBox;
    @FXML private Button quitButton;
    @FXML private Button roundStatsButton;
    @FXML private Button startButton;

    public void mouseClickActionPB() {
        playerBidText.setStyle("-fx-text-fill: black");
        playerBidText.setFont(Font.font("System", FontWeight.NORMAL, 16));

        if ((playerBidText.getText()).equals("Player Bid"))
            playerBidText.setText("");
    }

    public void mouseExitActionPB() {
        if ((playerBidText.getText()).equals("")) {
            playerBidText.setText("Player Bid");
            playerBidText.setStyle("-fx-text-fill: gray");
            playerBidText.setFont(Font.font("System Italic", FontPosture.ITALIC, 16));
        }
    }

    public void toggleButton() {
        group = new ToggleGroup();
        playerToggleButton.setToggleGroup(group);
        bankerToggleButton.setToggleGroup(group);

        if (playerToggleButton.isSelected()) {
            playerToggleButton.setStyle("-fx-background-color: #ed7117");
            bankerToggleButton.setStyle("-fx-background-color: orange");
        }

        else if (bankerToggleButton.isSelected()) {
            bankerToggleButton.setStyle("-fx-background-color: #ed7117");
            playerToggleButton.setStyle("-fx-background-color: orange");
        }

        else if (!(bankerToggleButton.isSelected() || playerToggleButton.isSelected())) {
            bankerToggleButton.setStyle("-fx-background-color: orange");
            playerToggleButton.setStyle("-fx-background-color: orange");
        }
    }

    public void quitButtonAction(Event e) throws IOException {
        Parent quitScreen = FXMLLoader.load(getClass().getResource("../QuitScreen/QuitScreen.fxml"));
        Scene quitScene = new Scene(quitScreen);

        Stage window = (Stage) ((Node)e.getSource()).getScene().getWindow();
        window.setScene(quitScene);
        window.setTitle("Quit game");
        window.show();
    }

    public void roundStatsButtonAction(Event e) throws IOException {
        Parent roundStatsScreen = FXMLLoader.load(getClass().getResource("../RoundStatsScreen/RoundStatsScreen.fxml"));
        Scene roundStatsScene = new Scene(roundStatsScreen);

        Stage window = (Stage) ((Node)e.getSource()).getScene().getWindow();
        window.setScene(roundStatsScene);
        window.setTitle("Round stats");
        window.show();
    }

    public void startButtonAction(Event e) throws IOException, ClassNotFoundException {
        int bid;
        try {
            bid = Integer.parseInt(playerBidText.getText());
        } catch (Exception err) {
            System.out.println("Bid value must be a number");
            return;
        }

        String hand = "Player";
        if (bankerToggleButton.isSelected())
            hand = "Banker";

        BaccaratInfo res = connection.send(bid, hand);
        System.out.printf("\nServer responded with: %d and %s\n\n", res.bid, res.hand);
    }
}
