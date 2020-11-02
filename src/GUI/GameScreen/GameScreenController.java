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

    private Image imageFromCard(String card) {
        String path = "./Cards/" + card + ".jpg";
        Image image = new Image(getClass().getResource(path).toExternalForm());
        return image;
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

        try {
            connection.send(bid, hand);
            BaccaratInfo res = connection.recieve();

            // Change banker cards
            this.bankerLeftCard.setImage(this.imageFromCard(res.bankerHand.get(0)));
            this.bankerRightCard.setImage(this.imageFromCard(res.bankerHand.get(1)));

            // Change player cards
            this.playerLeftCard.setImage(this.imageFromCard(res.playerHand.get(0)));
            this.playerRightCard.setImage(this.imageFromCard(res.playerHand.get(1)));

            // Display total winnings
            winningsCount += res.winnings;
            winningsLabel.setText("Winnings: $" + winningsCount);

            // Visual feedback showing winner
            if(res.winner.equals("Banker")) {
                bankerWinLabel.setText("Banker won!");
                bankerWinLabel.setStyle("-fx-color: #60b31d");
                playerWinLabel.setText("");
                bankerPane.setStyle("-fx-border-color: #60b31d; -fx-border-width: 7px");
                playerPane.setStyle("-fx-border-color: transparent");
            } else if(res.winner.equals("Player")) {
                playerWinLabel.setText("Player won!");
                playerWinLabel.setStyle("-fx-color: #60b31d");
                bankerWinLabel.setText("");
                playerPane.setStyle("-fx-border-color: #60b31d; -fx-border-width: 7px");
                bankerPane.setStyle("-fx-border-color: transparent");
            } else {
                playerWinLabel.setText("It's a Draw!");
                playerWinLabel.setStyle("-fx-text-fill: #daa520");
                bankerWinLabel.setText("It's a Draw!");
                bankerWinLabel.setStyle("-fx-text-fill: #daa520");
                bankerPane.setStyle("-fx-border-color: #daa520; -fx-border-width: 5px");
                playerPane.setStyle("-fx-border-color: #daa520; -fx-border-width: 5px");
            }

        } catch (Exception err) {
            System.out.println("Something went wrong while trying to send the request to the server");
            return;
        }
    }
}
