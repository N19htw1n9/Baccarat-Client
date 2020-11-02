package GUI.GameScreen;

import BaccaratGame.BaccaratInfo;
import GUI.Controller;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
    @FXML private ImageView bankerLeftCard;
    @FXML private ImageView bankerRightCard;
    @FXML private ImageView playerLeftCard;
    @FXML private ImageView playerRightCard;
    @FXML private Pane bankerPane;
    @FXML private Pane playerPane;
    @FXML private Label bankerWinLabel;
    @FXML private Label playerWinLabel;
    @FXML private Label winningsLabel;

    private double winningsCount = 0;

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

    public void startButtonAction(Event e) {
        double bid;
        try {
            bid = Double.parseDouble(playerBidText.getText());
        } catch (Exception err) {
            System.out.println("Bid value must be a number");
            return;
        }

        String hand = "Player";

        if (bankerToggleButton.isSelected())
            hand = "Banker";

        System.out.printf("\n\nBid: %f\nHand: %s\n", bid, hand);

        BaccaratInfo res;
        try {
            connection.send(bid, hand);
            System.out.println("Response:");
            res = connection.recieve();
            System.out.println("Banker hand: " + res.bankerHand);
            System.out.println("Player hand: " + res.playerHand);
            System.out.println("Winner: " + res.winner);
            System.out.println("Winnings: " + res.winnings);

            // Banker cards
            String BLCPath = "./Cards/" + res.bankerHand.get(0) + ".jpg";
            String BRCPath = "./Cards/" + res.bankerHand.get(1) + ".jpg";
            Image BLCPic = new Image(getClass().getResource(BLCPath).toExternalForm());
            Image BRCPic = new Image(getClass().getResource(BRCPath).toExternalForm());

            // Player cards
            String PLCPath = "./Cards/" + res.playerHand.get(0) + ".jpg";
            String PRCPath = "./Cards/" + res.playerHand.get(1) + ".jpg";
            Image PLCPic = new Image(getClass().getResource(PLCPath).toExternalForm());
            Image PRCPic = new Image(getClass().getResource(PRCPath).toExternalForm());

            // Change banker cards
            this.bankerLeftCard.setImage(BLCPic);
            this.bankerRightCard.setImage(BRCPic);

            // Change player cards
            this.playerLeftCard.setImage(PLCPic);
            this.playerRightCard.setImage(PRCPic);

            if(res.winner.equals("Banker"))
            {
                bankerWinLabel.setText("Banker won!");
                bankerWinLabel.setStyle("-fx-color: #60b31d");
                playerWinLabel.setText("");
                bankerPane.setStyle("-fx-border-color: #60b31d; -fx-border-width: 7px");
                playerPane.setStyle("-fx-border-color: transparent");
            }

            else if(res.winner.equals("Player"))
            {
                playerWinLabel.setText("Player won!");
                playerWinLabel.setStyle("-fx-color: #60b31d");
                bankerWinLabel.setText("");
                playerPane.setStyle("-fx-border-color: #60b31d; -fx-border-width: 7px");
                bankerPane.setStyle("-fx-border-color: transparent");

                winningsCount += res.bid;
                winningsLabel.setText("Winnings: $"+winningsCount);
            }

            else
            {
                playerWinLabel.setText("It's a Draw!");
                playerWinLabel.setStyle("-fx-text-fill: #daa520");
                bankerWinLabel.setText("It's a Draw!");
                bankerWinLabel.setStyle("-fx-text-fill: #daa520");
                bankerPane.setStyle("-fx-border-color: #daa520; -fx-border-width: 5px");
                playerPane.setStyle("-fx-border-color: #daa520; -fx-border-width: 5px");
            }

        } catch (Exception err) {
            System.out.println(err);
            System.out.println("Something went wrong while trying to send the request to the server");
            return;
        }
    }
}
