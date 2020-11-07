package GUI.RoundStatsScreen;

import BaccaratGame.BaccaratInfo;
import GUI.Controller;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class RoundStatsScreenController extends Controller {
    @FXML private Button backButton;
    @FXML private ListView roundStatsPane;

    public void backButtonAction(Event e) throws IOException {
        Parent gameScreen = FXMLLoader.load(getClass().getResource("../GameScreen/GameScreen.fxml"));
        Scene gameScene = new Scene(gameScreen);

        Stage window = (Stage) ((Node)e.getSource()).getScene().getWindow();
        window.setScene(gameScene);
        window.setTitle("Play game");
        window.show();
    }

    public void buildRoundInfoBox() {
        this.roundStatsPane.getItems().clear();
        for (int i = 0; i < this.roundStatsList.size(); i++) {
            this.roundStatsPane.getItems().add(this.getRoundInfo(this.roundStatsList.get(i), i+1));
        }
    }

    public Text getInfoText(String text) {
        Text info = new Text(text);
        info.setStyle("-fx-font-weight: bold;");
        return info;
    }

    public VBox getRoundInfo(BaccaratInfo roundInfo, int round) {
        double winnings = roundInfo.winnings;
        double bid = roundInfo.bid;
        String hand = roundInfo.hand;
        String winner = roundInfo.winner;

        VBox displayRoundInfo = new VBox(
                    new Text("Round: " + round),
                    new HBox(10, this.getInfoText("Hand:"), new Text(hand)),
                    new HBox(10, this.getInfoText("Bid placed:"), new Text("$" + bid)),
                    new HBox(10, this.getInfoText("Winner:"), new Text(winner)),
                    new HBox(10, this.getInfoText("Winnings:"), new Text("$" + winnings))
                );
        return displayRoundInfo;
    }
}
